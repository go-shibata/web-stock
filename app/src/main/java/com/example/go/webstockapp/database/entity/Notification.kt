package com.example.go.webstockapp.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "notification", indices = [Index(value = ["linkId"], unique = true)])
@ForeignKey(entity = Link::class, parentColumns = ["id"], childColumns = ["linkId"])
data class Notification(
    var savedDate: Date,
    var notifyDate: Date,
    var linkId: Long,
    var requestId: UUID,
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var isCompleted: Boolean = false
)