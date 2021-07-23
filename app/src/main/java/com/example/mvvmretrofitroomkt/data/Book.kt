package com.example.mvvmretrofitroomkt.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book(var title: String, var author: String?, var imageURL: String?) {

    @PrimaryKey(autoGenerate = true)
    var id = 0
}