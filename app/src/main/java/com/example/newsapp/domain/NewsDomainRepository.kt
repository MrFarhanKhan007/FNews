package com.example.newsapp.domain

import com.example.newsapp.domain.model.NewsArticle

interface NewsDomainRepository {
    suspend fun getNewsArticle(): List<NewsArticle>
}