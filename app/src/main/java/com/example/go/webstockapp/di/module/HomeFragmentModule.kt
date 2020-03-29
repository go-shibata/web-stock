package com.example.go.webstockapp.di.module

import com.example.go.webstockapp.di.component.HomeFragmentSubcomponent
import com.example.go.webstockapp.ui.home.HomeFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [HomeFragmentSubcomponent::class])
interface HomeFragmentModule {

    @Binds
    @IntoMap
    @ClassKey(HomeFragment::class)
    fun bindHomeFragmentSubcomponentFactory(
        factory: HomeFragmentSubcomponent.Factory
    ): AndroidInjector.Factory<*>
}