package com.example.lab5solution

import androidx.room.TypeConverter
import com.google.gson.Gson


class Converters {

    @TypeConverter
    fun arrayToString(value: Array<String>): String? {
        var genre = ""
        if (value != null) {
            if(value.isNotEmpty()) {
                for(items in value)
                    genre += "$items, "
            } else {
                genre = ""
            }
        }
        return genre
    }

    @TypeConverter
    fun stringTo(value: String) = Gson().fromJson(value,Array<String>::class.java).toList()
}