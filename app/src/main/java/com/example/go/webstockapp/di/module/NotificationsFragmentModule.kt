package com.example.go.webstockapp.di.module

import com.example.go.webstockapp.di.component.NotificationsFragmentSubcomponent
import com.example.go.webstockapp.ui.notifications.NotificationsFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [NotificationsFragmentSubcomponent::class])
interface NotificationsFragmentModule {

    @Binds
    @IntoMap
    @ClassKey(NotificationsFragment::class)
    fun bindNotificationsFragmentSubcomponentFactory(
        factory: NotificationsFragmentSubcomponent.Factory
    ): AndroidInjector.Factory<*>
}