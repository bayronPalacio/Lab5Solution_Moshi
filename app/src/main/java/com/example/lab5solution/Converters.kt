package com.example.lab5solution

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList


class Converters {
    companion object{
        @TypeConverter
        @JvmStatic
        fun fromList(value:List<String>):String{
            return value.toString()
        }

        @TypeConverter
        @JvmStatic
        fun toList(value:String):List<String>{
            return Gson().fromJson(value,Array<String>::class.java).toList()
        }
    }
}