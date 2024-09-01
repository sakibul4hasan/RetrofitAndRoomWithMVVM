package com.example.retrofitandroomwithmvvm.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.retrofitandroomwithmvvm.models.Result

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuotes(result: List<Result>)

    @Query("SELECT * FROM quote")
    suspend fun getQuotes(): List<Result>

}