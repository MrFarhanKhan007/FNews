package com.example.newsapp.presentation.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.newsapp.remote.paging.NewsPagingSource
import com.example.newsapp.use_case.GetNewsArticleUseCase
import com.example.newsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel
@Inject constructor(
    private val getNewsArticleUseCase: GetNewsArticleUseCase,
    private val newsPagingSource: NewsPagingSource // Inject PagingSource from your repository
) :
    ViewModel() {

    val articles = mutableStateOf(HomeStateHolder())

    init {
        getNewsArticles()
    }

    fun getNewsArticles() {
        getNewsArticleUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    articles.value = HomeStateHolder(isLoading = true)
                }

                is Resource.Success -> {
                    articles.value = HomeStateHolder(data = it.data)
                }

                is Resource.Error -> {
                    articles.value = HomeStateHolder(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    val newsPager = Pager(
        PagingConfig(pageSize = 20)
    ) {
        newsPagingSource
    }.flow.cachedIn(viewModelScope)

}