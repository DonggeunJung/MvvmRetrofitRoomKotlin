package com.example.mvvmretrofitroomkt.util

import com.example.mvvmretrofitroomkt.data.Api
import com.example.mvvmretrofitroomkt.data.Repository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun provideApi(): Api
    fun inject(repository: Repository)
}
