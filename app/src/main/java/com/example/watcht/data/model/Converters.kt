package com.example.watcht.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromString(value: String?): List<Int>? {
        if (!value.isNullOrEmpty()){
            val listType = object : TypeToken<List<Int>>() {}.type
            return Gson().fromJson(value, listType)
        }else{
            return null
        }

    }

    @TypeConverter
    fun fromList(list: List<Int>?): String? {
        if (!list.isNullOrEmpty()){
            val gson = Gson()
            return gson.toJson(list)
        }else{
            return null
        }

    }

}