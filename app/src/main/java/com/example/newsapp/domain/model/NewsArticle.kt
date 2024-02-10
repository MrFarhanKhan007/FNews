package com.example.newsapp.domain.model

data class NewsArticle(
    val author:String?,
    val title: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
)

// we took only these items only because these are the only parameters from the received json that will be shown on screen
// or will help us to redirect us to target page....