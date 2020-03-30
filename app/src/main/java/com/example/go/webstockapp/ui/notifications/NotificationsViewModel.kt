package com.example.go.webstockapp.ui.notifications

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.go.webstockapp.database.MyDatabase
import com.example.go.webstockapp.database.entity.Notification
import com.example.go.webstockapp.work.NotificationWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NotificationsViewModel @Inject constructor(
    app: Application
) : AndroidViewModel(app) {

    val notifications = MyDatabase.getInstance(app).notificationDao().getAllNotificationWithLink()

    fun editNotification(notification: NotificationAndLink, timeInMillis: Long) {
        cancelNotification(notification.notification)

        val link = notification.getLink()
        val request = OneTimeWorkRequestBuilder<NotificationWorker>()
            .setInputData(
                Data.Builder()
                    .putString(NotificationWorker.KEY_TITLE, link.title)
                    .putString(NotificationWorker.KEY_URL, link.url)
                    .putLong(NotificationWorker.KEY_ID, link.id)
                    .build()
            )
            .setInitialDelay(timeInMillis - System.currentTimeMillis(), TimeUnit.MILLISECONDS)
            .build()
        WorkManager.getInstance()
            .beginUniqueWork("notification", ExistingWorkPolicy.REPLACE, request)
            .enqueue()

        CoroutineScope(Dispatchers.Default).launch {
            val item = notification.notification.copy(
                savedDate = Date(System.currentTimeMillis()),
                notifyDate = Date(timeInMillis),
                isCompleted = false
            )
            MyDatabase.getInstance(getApplication())
                .notificationDao()
                .update(item)
        }
    }

    fun deleteNotification(notification: NotificationAndLink) {
        cancelNotification(notification.notification)
        CoroutineScope(Dispatchers.Default).launch {
            MyDatabase.getInstance(getApplication())
                .notificationDao()
                .delete(notification.notification)
        }
    }

    private fun cancelNotification(notification: Notification) {
        WorkManager.getInstance()
            .cancelWorkById(notification.requestId)
    }
}