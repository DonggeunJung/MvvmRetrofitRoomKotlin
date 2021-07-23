package com.example.mvvmretrofitroomkt.data

import androidx.room.*

@Dao
interface BookDao {
    @Insert
    fun insert(book: Book)

    @Update
    fun update(book: Book)

    @Delete
    fun delete(book: Book)

    // Get all data in table
    @Query("SELECT * FROM Book")
    fun getAll(): List<Book>

    @Query("DELETE FROM Book") // Delete all data in table
    fun delAll()

    @Query("SELECT COUNT(*) FROM Book") // Get count of data in table
    fun count(): Int
}