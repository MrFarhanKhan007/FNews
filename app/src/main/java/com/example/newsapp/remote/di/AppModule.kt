package com.example.newsapp.remote.di

import com.example.newsapp.remote.NewsApiService
import com.example.newsapp.remote.paging.NewsPagingSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    fun provideNewsApiService(): NewsApiService {
        val interceptor= HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        client.dispatcher.maxRequests = 1
        return Retrofit.Builder()
            .baseUrl(NewsApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)
    }

    @Provides
    fun providesnewsPagingSource(apiService: NewsApiService): NewsPagingSource {
        return NewsPagingSource(apiService)
    }


}