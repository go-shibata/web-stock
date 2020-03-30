package com.example.go.webstockapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.go.webstockapp.database.entity.Notification
import com.example.go.webstockapp.ui.notifications.NotificationAndLink

@Dao
interface NotificationDao {
    @Query("select * from notification")
    fun getAllNotifications(): LiveData<List<Notification>>

    @Transaction
    @Query("select * from notification")
    fun getAllNotificationWithLink(): LiveData<List<NotificationAndLink>>

    @Query("select * from notification where id = :id")
    fun getNotification(id: Long): LiveData<Notification>

    @Query("select * from notification where linkId = :linkId")
    fun getNotificationByLinkId(linkId: Long): Notification

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(notification: Notification)

    @Update
    fun update(notification: Notification)

    @Delete
    fun delete(notification: Notification)

    @Query("delete from notification")
    fun deleteAll()

    @Transaction
    fun setCompletedByLinkId(linkId: Long, isCompleted: Boolean) {
        val notification = getNotificationByLinkId(linkId)
        notification.isCompleted = isCompleted
        update(notification)
    }
}