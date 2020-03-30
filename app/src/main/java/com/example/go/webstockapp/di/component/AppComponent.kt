package com.example.go.webstockapp.di.component

import android.app.Application
import com.example.go.webstockapp.App
import com.example.go.webstockapp.di.module.HomeFragmentModule
import com.example.go.webstockapp.di.module.MainActivityModule
import com.example.go.webstockapp.di.module.NotificationsFragmentModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        MainActivityModule::class,
        HomeFragmentModule::class,
        NotificationsFragmentModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

    fun inject(app: App)
}