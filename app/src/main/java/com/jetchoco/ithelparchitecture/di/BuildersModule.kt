package com.jetchoco.ithelparchitecture.di

import com.jetchoco.ithelparchitecture.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}