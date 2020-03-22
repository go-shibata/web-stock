package com.example.go.webstockapp.di.component

import com.example.go.webstockapp.di.module.AppModule
import com.example.go.webstockapp.ui.home.HomeFragment
import com.example.go.webstockapp.ui.home.HomeViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(homeViewModel: HomeViewModel)
    fun inject(homeFragment: HomeFragment)
}