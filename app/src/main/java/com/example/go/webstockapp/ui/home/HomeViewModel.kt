package com.example.go.webstockapp.ui.home

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.go.webstockapp.database.MyDatabase
import com.example.go.webstockapp.database.entity.Link
import com.example.go.webstockapp.work.NotificationWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    app: Application
) : AndroidViewModel(app) {

    val links: LiveData<List<Link>> = MyDatabase.getInstance(app).linkDao().getAllLinks()

    fun insertLink(url: String) {
        CoroutineScope(Dispatchers.Default).launch {
            val title = withContext(Dispatchers.IO) {
                Jsoup.connect(url)
                    .get()
                    .getElementsByTag("title")
                    .text()
            }
            val link = Link(title, url, Date(System.currentTimeMillis()))
            MyDatabase.getInstance(getApplication())
                .linkDao()
                .insert(link)
        }
    }

    fun notifyLink(link: Link) {
        val request = OneTimeWorkRequestBuilder<NotificationWorker>()
            .setInputData(
                Data.Builder()
                    .putString(NotificationWorker.KEY_TITLE, link.title)
                    .putString(NotificationWorker.KEY_URL, link.url)
                    .build()
            )
            .setInitialDelay(5, TimeUnit.SECONDS)
            .build()
        WorkManager.getInstance()
            .beginUniqueWork("notification", ExistingWorkPolicy.REPLACE, request)
            .enqueue()
    }

    fun openLink(link: Link, context: Context) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link.url))
        context.startActivity(intent)
    }

    fun deleteLink(link: Link) {
        CoroutineScope(Dispatchers.Default).launch {
            MyDatabase.getInstance(getApplication())
                .linkDao()
                .delete(link)
        }
    }
}