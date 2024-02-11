package com.example.newsapp.remote.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.newsapp.remote.NewsApiService
import com.example.newsapp.remote.model.NewsArticleDTO
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class NewsRemoteMediator(
    private val newsApiService: NewsApiService
) : RemoteMediator<Int, NewsArticleDTO>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, NewsArticleDTO>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        (state.config.pageSize) + 1
                    }
                }
            } ?: 1 // Provide a default value of 1 if loadKey is null

            val response = newsApiService.getTopHeadlines(
                page = loadKey,
                pageSize = state.config.pageSize
            )

            if (response.isSuccessful) {
                val news = response.body()?.articles ?: emptyList()

                MediatorResult.Success(
                    endOfPaginationReached = news.isEmpty()
                )
            } else {
                MediatorResult.Error(HttpException(response))
            }
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}
