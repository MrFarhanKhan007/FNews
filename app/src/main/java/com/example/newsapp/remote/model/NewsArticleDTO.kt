package com.example.newsapp.remote.model


data class NewsArticleDTO(
    val source: NewsSourceDTO,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)
