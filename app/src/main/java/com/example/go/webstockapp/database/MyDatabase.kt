package com.example.go.webstockapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.go.webstockapp.database.dao.LinkDao
import com.example.go.webstockapp.database.dao.NotificationDao
import com.example.go.webstockapp.database.entity.Link
import com.example.go.webstockapp.database.entity.Notification

@Database(
    entities = [
        Link::class,
        Notification::class
    ], version = 1, exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class MyDatabase : RoomDatabase() {

    abstract fun linkDao(): LinkDao
    abstract fun notificationDao(): NotificationDao

    companion object {
        @Volatile
        private var instance: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase =
            instance ?: synchronized(this) {
                Room.databaseBuilder(
                        context,
                        MyDatabase::class.java,
                        "webStock.db"
                    ).build()
                    .also { instance = it }
            }
    }
}