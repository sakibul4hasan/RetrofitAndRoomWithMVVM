package com.example.retrofitandroomwithmvvm.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitandroomwithmvvm.R
import com.example.retrofitandroomwithmvvm.viewmodels.QuoteViewModel
import com.example.retrofitandroomwithmvvm.viewmodels.QuoteViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: QuoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //**
        val quoteRepository = (application as QuoteApplication).quoteRepository
        viewModel = ViewModelProvider(this,
            QuoteViewModelFactory(quoteRepository)).get(QuoteViewModel::class.java)

        viewModel.quotes.observe(this, Observer {
            Log.d("apiTest", it.results.toString())
            Toast.makeText(this@MainActivity, it.results.size.toString(), Toast.LENGTH_SHORT).show()
        })
    }
}