package com.example.retrofitandroomwithmvvm.api

import com.example.retrofitandroomwithmvvm.models.QuotesList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("quotes") //endpoint
    suspend fun getQuotes(@Query("page") page: Int): Response<QuotesList>
}