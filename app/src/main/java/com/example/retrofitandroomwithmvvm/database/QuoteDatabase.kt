package com.example.retrofitandroomwithmvvm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.retrofitandroomwithmvvm.models.Result

@Database(entities = [Result::class], version = 1)
@TypeConverters(Converters::class)
abstract class QuoteDatabase() : RoomDatabase() {

    abstract fun quoteDao(): QuoteDao

    companion object {
        @Volatile
        private var INSTANCE: QuoteDatabase? = null

        fun getDatabase(context: Context): QuoteDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        QuoteDatabase::class.java,
                        "quote_db")
                        .build()
                }
            }
            return INSTANCE!!
        }
    }

}