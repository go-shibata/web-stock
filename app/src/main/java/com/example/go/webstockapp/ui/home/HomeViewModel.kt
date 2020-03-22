package com.example.go.webstockapp.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.go.webstockapp.database.MyDatabase
import com.example.go.webstockapp.database.entity.Link
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    app: Application
) : AndroidViewModel(app) {

    val links: LiveData<List<Link>> = MyDatabase.getInstance(app).linkDao().getAllLinks()
}