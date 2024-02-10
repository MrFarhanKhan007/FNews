package com.example.newsapp.domain.mapper

import com.example.newsapp.domain.model.NewsArticle
import com.example.newsapp.remote.model.NewsArticleDTO

fun List<NewsArticleDTO>.toDomain(): List<NewsArticle> {
    return map {
        NewsArticle(
            title = it.title,
            url = it.url ?: "",
            urlToImage = it.urlToImage,
            publishedAt = it.publishedAt,
            author = it.author
        )
    }
}

