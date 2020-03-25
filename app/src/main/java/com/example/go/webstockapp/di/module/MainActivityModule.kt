package com.example.go.webstockapp.di.module

import com.example.go.webstockapp.MainActivity
import com.example.go.webstockapp.di.component.MainActivitySubcomponent
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [MainActivitySubcomponent::class])
interface MainActivityModule {

    @Binds
    @IntoMap
    @ClassKey(MainActivity::class)
    fun bindMainActivitySubcomponentFactory(
        factory: MainActivitySubcomponent.Factory
    ): AndroidInjector.Factory<*>
}