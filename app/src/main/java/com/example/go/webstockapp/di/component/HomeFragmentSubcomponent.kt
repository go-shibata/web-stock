package com.example.go.webstockapp.di.component

import com.example.go.webstockapp.ui.home.HomeFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface HomeFragmentSubcomponent : AndroidInjector<HomeFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<HomeFragment>
}