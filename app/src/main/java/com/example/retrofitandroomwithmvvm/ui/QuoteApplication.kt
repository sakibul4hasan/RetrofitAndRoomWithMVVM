package com.example.retrofitandroomwithmvvm.ui

import android.app.Application
import com.example.retrofitandroomwithmvvm.api.ApiService
import com.example.retrofitandroomwithmvvm.api.RetrofitClient
import com.example.retrofitandroomwithmvvm.database.QuoteDatabase
import com.example.retrofitandroomwithmvvm.repository.QuoteRepository

class QuoteApplication() : Application() {

    lateinit var quoteRepository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initialized()
    }

    private fun initialized() {
        val apiService = RetrofitClient.getRetrofit().create(ApiService::class.java)
        val quoteDatabase = QuoteDatabase.getDatabase(applicationContext)
        quoteRepository = QuoteRepository(apiService, quoteDatabase, applicationContext)
    }

}