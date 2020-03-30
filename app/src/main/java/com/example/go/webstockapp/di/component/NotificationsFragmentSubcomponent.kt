package com.example.go.webstockapp.di.component

import com.example.go.webstockapp.ui.notifications.NotificationsFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface NotificationsFragmentSubcomponent : AndroidInjector<NotificationsFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<NotificationsFragment>
}