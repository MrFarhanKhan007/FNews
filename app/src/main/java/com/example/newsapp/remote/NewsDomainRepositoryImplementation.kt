package com.example.newsapp.remote

import com.example.newsapp.domain.NewsDomainRepository
import com.example.newsapp.domain.mapper.toDomain
import com.example.newsapp.domain.model.NewsArticle
import com.example.newsapp.utils.SafeApiRequest
import javax.inject.Inject

class NewsDomainRepositoryImplementation @Inject constructor(private val apiService: NewsApiService) :
    NewsDomainRepository, SafeApiRequest() {
    override suspend fun getNewsArticle(): List<NewsArticle> {
        val response = safeApiRequest {
            apiService.getTopHeadlines()
        }

        return response.articles.toDomain()

    }
}