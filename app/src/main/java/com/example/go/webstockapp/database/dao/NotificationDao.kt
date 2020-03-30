package com.example.go.webstockapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.go.webstockapp.database.entity.Notification

@Dao
interface NotificationDao {
    @Query("select * from notification")
    fun getAllNotifications(): LiveData<List<Notification>>

    @Query("select * from notification where id = :id")
    fun getNotification(id: Long): LiveData<Notification>

    @Insert
    fun insert(notification: Notification)

    @Update
    fun update(notification: Notification)

    @Delete
    fun delete(notification: Notification)

    @Query("delete from notification")
    fun deleteAll()
}