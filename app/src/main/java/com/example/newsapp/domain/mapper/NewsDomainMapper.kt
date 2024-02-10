package com.example.newsapp.domain.mapper

import com.example.newsapp.domain.model.NewsArticle
import com.example.newsapp.remote.model.NewsArticleDTO

fun List<NewsArticleDTO>.toDomain(): List<NewsArticle> {
    return map {
        NewsArticle(
            title = it.title?:"",
            description = it.description?:"",
            url = it.url?:"",
            urlToImage = it.urlToImage?:"",
            publishedAt = it.publishedAt?:"",
            content = it.content?:""
        )
    }
}

fun NewsArticleDTO.toDomainSecondary(): NewsArticle {
    return NewsArticle(
        title = title?:"",
        description = description?:"",
        url = url?:"",
        urlToImage = urlToImage?:"",
        publishedAt = publishedAt?:"",
        content = content?:""
    )
}

