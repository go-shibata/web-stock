package com.example.go.webstockapp.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "link")
data class Link(
    var title: String,
    var url: String,
    var savedDate: Date,
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var isChecked: Boolean = false,
    var checkedDate: Date? = null,
    var notifyDate: Date? = null
) {
    val domain: String?
        get() {
            val regex = Regex("//([a-z0-9.]+)")
            return regex.find(url)?.groupValues?.get(1)
        }
}