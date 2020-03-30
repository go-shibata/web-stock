package com.example.go.webstockapp.ui.notifications

import androidx.room.Embedded
import androidx.room.Relation
import com.example.go.webstockapp.database.entity.Link
import com.example.go.webstockapp.database.entity.Notification

class NotificationAndLink {
    @Embedded
    lateinit var notification: Notification

    @Relation(parentColumn = "linkId", entityColumn = "id")
    lateinit var link: List<Link>

    fun getLink(): Link = link.single()
}