package com.example.shoppingapp.utility

import androidx.room.TypeConverter
import java.util.*

object DateConverter {

    @JvmStatic
    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return if (dateLong == null) null else Date(dateLong)
    }
    @JvmStatic
    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

}