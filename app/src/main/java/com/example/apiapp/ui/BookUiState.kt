package com.example.apiapp.ui

import com.example.apiapp.Data.Book


enum class QueryStatus{
    LOADING,
    ERROR,
    DONE,
    IDLE
}
data class BookUiState(
    val queryText: String= "",
    val result: String?=null,
    val resultList: List<Book> = emptyList(),
    val queryStatus: QueryStatus = QueryStatus.IDLE
)

