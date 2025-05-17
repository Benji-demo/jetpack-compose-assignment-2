package com.github.actions.network

import com.github.actions.model.Todo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import kotlin.jvm.java

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface TodoApiService {
    @GET("todos")
//    suspend fun getTodos() : Todo
    suspend fun getTodos(): List<Todo>
}

object TodoApi {
    val retrofitService : TodoApiService by lazy {
        retrofit.create(TodoApiService::class.java)
    }
}