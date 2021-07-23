package com.example.mvvmretrofitroomkt.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mvvmretrofitroomkt.R
import com.example.mvvmretrofitroomkt.data.Book
import com.example.mvvmretrofitroomkt.databinding.BookListItemBinding
import com.example.mvvmretrofitroomkt.util.InjectorUtils

class BookRVAdapter(private var parent: AppCompatActivity) :
    RecyclerView.Adapter<BookRVAdapter.BookVH>() {
    private val viewModel  by parent.viewModels <BookViewModel> { InjectorUtils.provideViewModelFactory() }
    private val instance: RecyclerView.Adapter<BookVH> = this

    // Return list items count
    override fun getItemCount(): Int {
        // When the data object is not exist return 0
        return viewModel.getBooks().value?.let { it.size } ?: 0
    }

    // Make ViewHolder & View binding object
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BookVH {
        // Get the view binding object of custom list item layout
        val inflater = LayoutInflater.from(parent)
        val binding: BookListItemBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.book_list_item, viewGroup, false
        )
        // Set the Lifecycle Owner of View binding to fragment
        binding.lifecycleOwner = parent
        // Set ViewModel object to binding object as a variable
        binding.viewModel = viewModel

        // Make ViewHolder object
        return BookVH(binding)
    }

    // When ViewHolder is binded set data to binding object
    override fun onBindViewHolder(viewHolder: BookVH, position: Int) {
        // Set item index number to binding object
        viewHolder.bind(position)
    }

    // Reuse views
    class BookVH(binding: BookListItemBinding) : ViewHolder(binding.root) {
        var binding: BookListItemBinding = binding

        fun bind(index: Int) {
            binding.index = index
            binding.executePendingBindings()
        }
    }

    // Constructor
    init {
        // make School simple data list Observer object
        val booksObserver = Observer<List<Book>> { // When School simple data list is changed update RecyclerView
            instance.notifyDataSetChanged()
        }

        // Send Observer object to ViewModel
        viewModel.getBooks().observe(parent, booksObserver)
        // Request Card data list to Repository
        viewModel.reqBooks()
    }
}
