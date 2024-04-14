package com.example.apiapp.network

import com.example.apiapp.Data.Volume
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//  'https://books.googleapis.com/books/v1/volumes?q=war%20breaker&maxResults=10&printType=BOOKS&projection=FULL&key=[YOUR_API_KEY]'
const val BASE_URL = "https://books.googleapis.com/books/v1/"
const val QUERY_PARA = "q"
const val MAX_RESULT = "maxResults"
const val PRINT_TYPE = "printType"

val moshi :Moshi= Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface BookApiService {
    @GET("volumes") // to do verify question mark will be present or not
    suspend fun getBooks(
        //suspend means it will not call at front for thread
        @Query(QUERY_PARA)
        query: String,
        @Query(MAX_RESULT)
        results: String,
        @Query(PRINT_TYPE)
        type: String,
    ): Volume
}

object BookApi {
    val retrofitService: BookApiService by lazy {
        retrofit.create(BookApiService::class.java)
    }
}
