package com.example.newsapp.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.domain.mapper.toDomain
import com.example.newsapp.domain.model.NewsArticle
import com.example.newsapp.remote.NewsApiService

class NewsPagingSource(private val newsApiService: NewsApiService) :
    PagingSource<Int, NewsArticle>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsArticle> {
        val page = params.key ?: 1
        return try {
            val response = newsApiService.getTopHeadlines(page = page, pageSize = params.loadSize)
            val news = response.body()?.articles?.toDomain() ?: emptyList()
            LoadResult.Page(
                data = news,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (news.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, NewsArticle>): Int? {
        return state.anchorPosition?.let {
            val page = state.closestPageToPosition(it)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }
}
