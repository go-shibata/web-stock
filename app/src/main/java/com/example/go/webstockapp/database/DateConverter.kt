package com.example.go.webstockapp.database

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun toTime(date: Date?): Long? = date?.time

    @TypeConverter
    fun fromTime(time: Long?): Date? = time?.let { Date(it) }
}