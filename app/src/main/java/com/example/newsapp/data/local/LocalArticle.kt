package com.example.newsapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class LocalArticle(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,

//    val newsId: Long, // Foreign key to associate with LocalNews
//    val author: String,

    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)