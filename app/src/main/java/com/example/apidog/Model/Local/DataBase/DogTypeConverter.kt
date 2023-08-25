package com.example.apidog.Model.Local.DataBase

import android.os.Message
import androidx.room.TypeConverter
import com.google.gson.Gson

class DogTypeConverter {

    @TypeConverter
    fun fromCustomType(value: Any): String? {

        return value?.toString()
    }
    fun toCustomType(value: String?): Any? {
        // Realiza la conversión de String a objeto Message
        // Por ejemplo, podrías deserializar el JSON en un objeto Message
        return null
    }
}
