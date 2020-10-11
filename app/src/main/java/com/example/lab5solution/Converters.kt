package com.example.lab5solution

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {
    companion object{
        @TypeConverter
        @JvmStatic
        fun fromList(value:List<String>): String {
//            var genreString = ""
//            for(item in value){
//                genreString += "$item, "
//            }
//            return genreString
            return value.toString()
        }

        @TypeConverter
        @JvmStatic
        fun toList(value:String):List<String>{
            return Gson().fromJson(value,Array<String>::class.java).toList()
        }


    }
}