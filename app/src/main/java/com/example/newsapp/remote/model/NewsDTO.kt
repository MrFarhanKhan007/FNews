package com.example.newsapp.remote.model

data class NewsDTO(
    val status: String?,
    val totalResults: Int?,
    val articles: List<NewsArticleDTO>
)
