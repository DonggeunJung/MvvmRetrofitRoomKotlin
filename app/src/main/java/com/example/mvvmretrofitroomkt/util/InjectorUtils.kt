package com.example.mvvmretrofitroomkt.util

import com.example.mvvmretrofitroomkt.App
import com.example.mvvmretrofitroomkt.data.BookDao
import com.example.mvvmretrofitroomkt.data.BookDatabase
import com.example.mvvmretrofitroomkt.data.Repository

object InjectorUtils {

    fun provideViewModelFactory(): ViewModelFactory {
        val dao: BookDao = BookDatabase.getInstance(App.context).dao()!!
        val repository: Repository = Repository.getInstance(dao)
        return ViewModelFactory(repository)
    }
}