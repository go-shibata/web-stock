package com.example.go.webstockapp.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "notification")
@ForeignKey(entity = Link::class, parentColumns = ["id"], childColumns = ["linkId"])
class Notification(
    var notifyDate: Date? = null,
    var linkId: Long,
    var requestId: UUID,
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var isCompleted: Boolean = false
)