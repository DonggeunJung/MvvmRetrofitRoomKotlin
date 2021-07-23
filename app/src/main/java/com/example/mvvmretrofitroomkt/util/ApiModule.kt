package com.example.mvvmretrofitroomkt.util

import com.example.mvvmretrofitroomkt.data.Api
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class ApiModule {
    @Provides
    @Singleton
    fun provideApi(): Api {
        // Make Retrofit API object & return
        return Api.retrofit.create(Api::class.java)
    }
}
