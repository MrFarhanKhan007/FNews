package com.example.newsapp.remote

import com.example.newsapp.remote.model.NewsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("apiKey")
        apiKey: String = "ca58aeea158b4ad5bef296396e422b1a",

        @Query("country")
        country: String="in",

        @Query("category")
        category: String="general",

        @Query("pageSize") //paging
        pageSize: Int=20,

        @Query("page")
        page: Int=1

    ): Response<NewsDTO>

    companion object{
        const val BASE_URL="https://newsapi.org/v2/"
    }
}