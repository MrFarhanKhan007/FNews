package com.example.newsapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.newsapp.domain.model.NewsArticle

@Composable
fun HomeScreen() {
    MyApp(Modifier)
}

@Composable
fun MyApp(modifier: Modifier) {
    val homeScreenViewModel = hiltViewModel<HomeScreenViewModel>()
    val res = homeScreenViewModel.articles.value

    if (res.isLoading) {
        Box(modifier = modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier.align(Alignment.Center))
        }
    }

    if (res.error.isNotBlank()) {
        Box(modifier.fillMaxSize()) {
            Text(text = res.error, modifier.align(Alignment.Center))
        }
    }

    res.data?.let {
        LazyColumn {
            items(it) {
                NewsArticleItem(Modifier, it)
            }
        }
    }
}

@Composable
fun NewsArticleItem(modifier: Modifier, it: NewsArticle) {
    Column {
        Image(
            painter = rememberAsyncImagePainter(model = it.urlToImage),
            contentDescription = null,
            modifier
                .height(300.dp)
                .fillMaxWidth()
        )


        it.title?.let { it1 ->
            Text(
                text = it1,
                modifier.padding(12.dp),
                style = TextStyle(
                    color = Color.Gray,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}