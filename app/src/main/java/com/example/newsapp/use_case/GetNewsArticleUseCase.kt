package com.example.newsapp.use_case

import com.example.newsapp.domain.NewsDomainRepository
import com.example.newsapp.domain.model.NewsArticle
import com.example.newsapp.utils.Resource
import com.example.newsapp.utils.Resource.Error
import com.example.newsapp.utils.Resource.Loading
import com.example.newsapp.utils.Resource.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNewsArticleUseCase
@Inject constructor(private val newsDomainRepository: NewsDomainRepository) {
    operator fun invoke(): Flow<Resource<List<NewsArticle>>> = flow {
        emit(Loading())
        try {
            emit(Success(newsDomainRepository.getNewsArticle()))
        } catch (e: Exception) {
            emit(Error(e.message))
        }
    }
}