package com.example.newsapp.domain.di

import com.example.newsapp.domain.NewsDomainRepository
import com.example.newsapp.remote.NewsApiService
import com.example.newsapp.remote.NewsDomainRepositoryImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    fun providesNewsDomainRepository(apiService: NewsApiService): NewsDomainRepository {
        return NewsDomainRepositoryImplementation(apiService = apiService)
    }

}