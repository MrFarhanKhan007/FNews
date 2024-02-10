package com.example.newsapp.presentation.screens

import com.example.newsapp.domain.model.NewsArticle

data class HomeStateHolder(
    val isLoading: Boolean = false,
    val data: List<NewsArticle>? = null,
    val error: String = ""
)
