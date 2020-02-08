package com.jetchoco.ithelparchitecture.di

import com.jetchoco.ithelparchitecture.api.GithubService
import com.jetchoco.ithelparchitecture.util.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }

    @Singleton
    @Provides
    fun provideGithubService(okHttpClient: OkHttpClient): GithubService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(GithubService::class.java)
    }
}