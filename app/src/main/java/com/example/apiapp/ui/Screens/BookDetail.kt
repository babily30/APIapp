package com.example.apiapp.ui.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.apiapp.Data.Book
import com.example.apiapp.ui.BookUiState

@Composable
fun BookDetailScreen(
    uiState: BookUiState,
    modifier: Modifier = Modifier,
    onBackPress: () -> Unit,
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)){
        
        IconButton(onClick = onBackPress) {
            androidx.compose.material3.Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription ="back" )
        }
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp))
        {
            items(uiState.resultList){
                BookCard(
                    book = it,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun BookCard(book: Book, modifier: Modifier) {
    Card(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
        GlideImage(model = book.volumeInfo?.imageLinks?.smallThumbnail,
            contentDescription =book.volumeInfo?.title?:"",
            modifier = Modifier.padding(8.dp),
            contentScale = ContentScale.Crop)
        Text(text = book.volumeInfo?.title?:"",)
        Text(
            text = book.volumeInfo?.title ?:"",
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Preview
@Composable
fun BookDetailScreenPreview() {
    BookDetailScreen(uiState = BookUiState(
        queryText = "Android",
        result = "Here is the result",
    ),
        onBackPress = { }

    )
}