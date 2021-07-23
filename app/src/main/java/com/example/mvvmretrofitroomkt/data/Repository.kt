package com.example.mvvmretrofitroomkt.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmretrofitroomkt.util.ApiComponent
import com.example.mvvmretrofitroomkt.util.DaggerApiComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository(private val bookDao: BookDao) {
    @Inject
    lateinit var api: Api
    private val ldBooks = MutableLiveData<List<Book>>()

    fun getBooks(): LiveData<List<Book>> {
        return ldBooks as LiveData<List<Book>>
    }

    // Start getting Book list from server or DB
    fun reqBooks() {
        // Check whether Local DB has data
        CoroutineScope(IO).launch {
            val count = bookDao.count()

            withContext(Main) {
                getBookList(count)
            }
        }
    }

    // Determine data resource - Server or Local DB
    private fun getBookList(count: Int) {
        if (count < 1) {
            getBookListFromServer()
        } else {
            getBookListFromDb()
        }
    }

    // Get data from server
    private fun getBookListFromServer() {
        val call: Call<List<Book>> = api.books
        call.enqueue(object : Callback<List<Book>> {
            override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                // When completed, get data & save
                val bookList = response.body()
                onDataCompleted(bookList)
            }

            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                Log.d("tag", t.message!!)
            }
        })
    }

    // When completed, get data & save
    private fun onDataCompleted(bookList: List<Book>?) {
        // If Card data is exist send data to listener
        if (bookList != null && bookList.isNotEmpty()) {
            ldBooks.value = bookList!!

            // Save Card list to DB
            cards2db(bookList)
        } else {
            getBookListFromDb()
        }
    }

    // Save Card list to DB
    private fun cards2db(bookList: List<Book>): Int {
        // Read data from DB
        CoroutineScope(IO).launch {
            // Delete current data in DB
            bookDao.delAll()
            // Save new Card data one by one
            bookList.forEach { book ->
                bookDao.insert(book)
            }
        }
        return bookList.size
    }

    // Get data from DB
    private fun getBookListFromDb() {
        CoroutineScope(IO).launch {
            // Read data from DB
            val bookList = bookDao.getAll()

            withContext(Main) {
                ldBooks.value = bookList
            }
        }
    }

    companion object {
        private var instance: Repository? = null

        fun getInstance(bookDao: BookDao): Repository {
            return instance ?: Repository(bookDao).also { instance = it }
        }
    }

    init {
        val component: ApiComponent = DaggerApiComponent.builder().build()
        component.inject(this)
    }
}
