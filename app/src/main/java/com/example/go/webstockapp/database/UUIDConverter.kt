package com.example.go.webstockapp.database

import androidx.room.TypeConverter
import java.util.*

class UUIDConverter {
    @TypeConverter
    fun toString(uuid: UUID): String = uuid.toString()

    @TypeConverter
    fun fromString(string: String): UUID = UUID.fromString(string)
}