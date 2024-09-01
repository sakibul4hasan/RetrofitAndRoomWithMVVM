package com.example.retrofitandroomwithmvvm.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofitandroomwithmvvm.api.ApiService
import com.example.retrofitandroomwithmvvm.database.QuoteDatabase
import com.example.retrofitandroomwithmvvm.models.QuotesList
import com.example.retrofitandroomwithmvvm.utils.NetworkUtils

class QuoteRepository(private val apiService: ApiService, private val quoteDatabase: QuoteDatabase, private val context: Context) {

    private val _quoteLiveData = MutableLiveData<QuotesList>()
    val quoteLiveData: LiveData<QuotesList>
        get() = _quoteLiveData

    suspend fun getQuotes(page: Int) {

        if (NetworkUtils.isInternetAvailable(context)) {
            val response = apiService.getQuotes(page)
            if (response.isSuccessful && response.body() != null) {
                _quoteLiveData.postValue(response.body())
                quoteDatabase.quoteDao().addQuotes(response.body()!!.results)
            }
        }
        else {
            val results = quoteDatabase.quoteDao().getQuotes()
            val quotesList = QuotesList(1,1,1,results,1,1)
            _quoteLiveData.postValue(quotesList)
        }

    }

}