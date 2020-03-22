package com.example.go.webstockapp.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.go.webstockapp.database.entity.Link
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    app: Application
) : AndroidViewModel(app) {

    val links: LiveData<List<Link>> = MutableLiveData(
        listOf(
            Link(0, "http://www.hoge.com/"),
            Link(1, "http://www.fuga.com/"),
            Link(2, "http://www.piyo.com/")
        )
    )
}