package com.example.go.webstockapp.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "link")
data class Link(
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    var url: String
)