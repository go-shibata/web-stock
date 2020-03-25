package com.example.go.webstockapp.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "link")
data class Link(
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    var url: String,
    var savedDate: Date,
    var isChecked: Boolean = false,
    var checkedDate: Date? = null,
    var notifyDate: Date? = null
)