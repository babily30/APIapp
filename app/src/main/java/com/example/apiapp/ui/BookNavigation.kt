package com.example.apiapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apiapp.ui.Screens.BookDetailScreen
import com.example.apiapp.ui.Screens.BookSearchScreen

//Screen
enum class Screen {
    Search,
    Detail
}

@Composable
fun BookNavigation(
    startDestination: String = Screen.Search.name,
) {
    val viewModel = viewModel<BookViewModel>()
    val uiState = viewModel.uiState.collectAsState().value

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Search.name) {
            BookSearchScreen(
                uiState = uiState,
                onQueryChange = viewModel::onQueryChange,
                onSubmit = {
                    viewModel.searchBooks()
                    navController.navigate(Screen.Detail.name)
                }
            )
        }
        composable(Screen.Detail.name) {
            BookDetailScreen(uiState = uiState,
                onBackPress = {
                    navController.popBackStack()
                }
            )
        }
    }
}