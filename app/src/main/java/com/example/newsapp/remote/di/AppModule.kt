package com.example.newsapp.remote.di

import com.example.newsapp.remote.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    fun provideNewsApiService(): NewsApiService {
        return Retrofit.Builder()
            .baseUrl(NewsApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)
    }

//    @Singleton
//    fun provideNewsDomainRepository(): NewsDomainRepository {
//        return NewsDomainRepositoryImplementation()
//    }

}