package com.example.go.webstockapp.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.go.webstockapp.database.MyDatabase
import com.example.go.webstockapp.database.entity.Link
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import java.util.*
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

    fun deleteLink(link: Link) {
        CoroutineScope(Dispatchers.Default).launch {
            MyDatabase.getInstance(getApplication())
                .linkDao()
                .delete(link)
        }
    }
}