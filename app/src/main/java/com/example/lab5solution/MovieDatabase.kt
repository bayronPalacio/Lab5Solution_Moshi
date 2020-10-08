package com.example.lab5solution

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.lab5solution.data.Movie

@Database(entities = [Movie::class], version = 1)
//@TypeConverters(Converters::class)

abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao() : MovieDao

    companion object {
        var INSTANCE: MovieDatabase? = null

        fun getAppDataBase(context: Context): MovieDatabase? {
            if (INSTANCE == null){
                synchronized(MovieDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, MovieDatabase::class.java, "movie")
                        .fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE
        }
        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}