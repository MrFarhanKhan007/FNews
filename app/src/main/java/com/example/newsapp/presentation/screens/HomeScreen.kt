package com.example.newsapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.newsapp.R
import com.example.newsapp.domain.model.NewsArticle
import com.example.newsapp.ui.theme.backgroundColor
import com.example.newsapp.ui.theme.contentColor
import com.example.newsapp.ui.theme.raleway
import com.example.newsapp.ui.theme.textColor

@Composable
fun HomeScreen() {
    MyApp(Modifier)
}

@Composable
fun MyApp(modifier: Modifier) {
    val homeScreenViewModel = hiltViewModel<HomeScreenViewModel>()
    val res = homeScreenViewModel.articles.value

    Scaffold(
        topBar = {
            Box(
                modifier
                    .fillMaxWidth()
                    .background(backgroundColor)
            ) {
                Text(
                    text = "FNews",
                    modifier.padding(15.dp),
                    style = TextStyle(
                        fontSize = 50.sp,
                        fontFamily = raleway
                    ),
                    color = textColor
                )

            }
        },
        containerColor = backgroundColor,
        contentColor = backgroundColor
    ) { contentPadding ->

        if (res.isLoading) {
            Box(modifier = modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }
        }

        if (res.error.isNotBlank()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Oops! It seems like you're offline." +
                                " Please check your internet connection and try again" +
                                " by clicking the 'Reload' button below.\"",
                        modifier.padding(12.dp),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = raleway,
                            textAlign = TextAlign.Center
                        ),
                        color = textColor
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            homeScreenViewModel.getNewsArticles()
                        },
                        shape = RoundedCornerShape(corner = CornerSize(7.dp)),
                        colors = ButtonDefaults.buttonColors(textColor)
                    ) {
                        Text(
                            text = "Reload",
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontFamily = raleway,
                            )
                        )
                    }
                }
            }
        }

        res.data?.let {
            LazyColumn(modifier.padding(contentPadding)) {
                items(it) {
                    NewsArticleItem(Modifier, it)
                }
            }
        }
    }

}

@Composable
fun NewsArticleItem(modifier: Modifier, it: NewsArticle) {
    val uriHandler = LocalUriHandler.current

    Card(
        modifier
            .fillMaxWidth()
            .padding(12.dp)
            .height(120.dp)
            .clickable {
                it.url?.let { it1 -> uriHandler.openUri(it1) }
            }, // Increased height for better visibility and aesthetics
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(contentColor),
        elevation = CardDefaults.cardElevation(3.dp), // Adding elevation for a lifted look
    ) {
        Row(
            modifier.padding(all = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            it.urlToImage?.let { imageUrl ->
                Image(
                    painter = rememberAsyncImagePainter(model = imageUrl),
                    contentDescription = null,
                    modifier
                        .size(100.dp)
                        .clip(shape = RoundedCornerShape(5.dp)),
                    contentScale = ContentScale.Crop // F
                )
            } ?: Image(
                painter = painterResource(R.drawable.errorimage),
                contentDescription = null,
                modifier.requiredSize(100.dp),
            )

            Spacer(modifier.width(10.dp))


            LazyColumn {
                items(1) { lazyScope ->

                    it.author?.let { author ->
                        Text(
                            text = author,
                            style = TextStyle(
                                color = Color.Gray, // Changed text color for better readability
                                fontWeight = FontWeight.Bold, // Increased font weight for emphasis
                                fontSize = 14.sp // Adjusted font size for better visibility
                            )
                        )
                    } ?: Text(
                        "N/A",
                        style = TextStyle(
                            color = Color.Gray, // Changed text color for better readability
                            fontWeight = FontWeight.Bold, // Increased font weight for emphasis
                            fontSize = 14.sp // Adjusted font size for better visibility
                        )
                    )

                    Spacer(modifier.height(4.dp))

                    it.title?.let { title ->
                        Text(
                            text = title,
                            style = TextStyle(
                                color = textColor, // Changed text color for better readability
                                fontWeight = FontWeight.Bold, // Increased font weight for emphasis
                                fontSize = 14.sp // Adjusted font size for better visibility
                            )
                        )
                    }

                    Spacer(modifier.height(4.dp))

                    it.publishedAt?.let { publishedAt ->
                        Text(
                            text = publishedAt,
                            style = TextStyle(
                                color = Color.Gray, // Changed text color for better readability
                                fontWeight = FontWeight.Normal, // Normal font weight for less emphasis
                                fontSize = 12.sp // Adjusted font size for better visibility
                            )
                        )
                    }


                }


            }

        }
    }
}


@Preview
@Composable
fun NewsArticleItemPreview() {
    NewsArticleItem(
        modifier = Modifier,
        it = NewsArticle(
            author = "LiveMint",
            title = "Govt push puts spotlight on cervical cancer, experts hope it is possible " +
                    "turning points",
            urlToImage = R.drawable.errorimage.toString(),
            url = "https://www.washingtonpost.com/world/2024/02/09/hostage-train-switzerland-ax-knife/",
            publishedAt = "2024-02-09T13:34:00Z"
        )
    )
}