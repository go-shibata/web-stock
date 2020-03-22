package com.example.go.webstockapp.di.module

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: Application) {
    @Provides
    fun provideApplication() = app
}