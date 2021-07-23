package com.example.mvvmretrofitroomkt.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.example.mvvmretrofitroomkt.data.Repository
import com.example.mvvmretrofitroomkt.ui.BookViewModel

class ViewModelFactory(private val repository: Repository)
    : NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == BookViewModel::class.java) {
            return BookViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: \${modelClass.name}")
    }
}
