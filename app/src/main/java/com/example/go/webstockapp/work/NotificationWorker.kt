package com.example.go.webstockapp.work

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.go.webstockapp.R
import com.example.go.webstockapp.database.MyDatabase
import com.example.go.webstockapp.ui.MainActivity

class NotificationWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        val linkTitle = checkNotNull(inputData.getString(KEY_TITLE))
        val linkUrl = checkNotNull(inputData.getString(KEY_URL))
        val linkId = checkNotNull(inputData.getLong(KEY_ID, 0))

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager =
                applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel =
                NotificationChannel("link", "Link", NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = "Link channel"
            manager.createNotificationChannel(channel)
        }

        val intent = Intent(applicationContext, MainActivity::class.java)
        val contentIntent =
            PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val notification = NotificationCompat.Builder(applicationContext, "link")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(linkTitle)
            .setContentText(applicationContext.getString(R.string.notification_small_message))
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(
                        applicationContext.getString(
                            R.string.notification_big_message,
                            linkUrl
                        )
                    )
            )
            .setContentIntent(contentIntent)
            .setAutoCancel(true)
            .build()
        NotificationManagerCompat.from(applicationContext)
            .notify(1, notification)

        MyDatabase.getInstance(applicationContext)
            .notificationDao()
            .setCompletedByLinkId(linkId, true)

        return Result.success()
    }

    companion object {
        const val KEY_TITLE = "title"
        const val KEY_URL = "url"
        const val KEY_ID = "id"
    }
}