package com.example.mvvmretrofitroomkt.data

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Api {
    @get:GET("books.json")
    val books: Call<List<Book>>

    companion object {
        // Make Retrofit object
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
            .build()

        // Hero Full URL : https://simplifiedcoding.net/demos/marvel/
        // BookStore Full URL : http://de-coding-test.s3.amazonaws.com/books.json
        private const val BASE_URL = "http://de-coding-test.s3.amazonaws.com/"

        //val instance = Api.retrofit.create(Api::class.java)
    }
}
