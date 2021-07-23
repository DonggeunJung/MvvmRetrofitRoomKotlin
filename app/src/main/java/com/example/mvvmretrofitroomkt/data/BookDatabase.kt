package com.example.mvvmretrofitroomkt.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Book::class], version = 1)
abstract class BookDatabase : RoomDatabase() {
    abstract fun dao(): BookDao?

    companion object {
        @Volatile private var instance: BookDatabase? = null

        fun getInstance(context: Context): BookDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookDatabase::class.java, "book_database"
                ).build()
            }
            return instance!!
        }
    }
}
