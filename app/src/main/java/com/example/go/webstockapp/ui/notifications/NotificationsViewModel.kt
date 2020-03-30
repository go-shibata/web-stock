package com.example.go.webstockapp.ui.notifications

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.work.WorkManager
import com.example.go.webstockapp.database.MyDatabase
import com.example.go.webstockapp.database.entity.Notification
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotificationsViewModel @Inject constructor(
    app: Application
) : AndroidViewModel(app) {

    val notifications = MyDatabase.getInstance(app).notificationDao().getAllNotificationWithLink()

    fun editNotification(notification: NotificationAndLink) {
        TODO()
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