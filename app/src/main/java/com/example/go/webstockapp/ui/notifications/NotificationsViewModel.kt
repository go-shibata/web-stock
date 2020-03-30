package com.example.go.webstockapp.ui.notifications

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.go.webstockapp.database.MyDatabase
import javax.inject.Inject

class NotificationsViewModel @Inject constructor(
    app: Application
) : AndroidViewModel(app) {

    val notifications = MyDatabase.getInstance(app).notificationDao().getAllNotifications()
}