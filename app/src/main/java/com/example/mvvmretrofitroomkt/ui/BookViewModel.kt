package com.example.mvvmretrofitroomkt.ui

import androidx.lifecycle.ViewModel
import com.example.mvvmretrofitroomkt.data.Repository

class BookViewModel(private val repository: Repository) : ViewModel() {

    // Return Book data list
    fun getBooks() = repository.getBooks()

    // Request Card data list to Repository
    fun reqBooks() {
        repository.reqBooks()
    }

}
