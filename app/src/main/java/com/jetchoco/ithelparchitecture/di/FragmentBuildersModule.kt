package com.jetchoco.ithelparchitecture.di

import com.jetchoco.ithelparchitecture.ui.RepoFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeRepoFragment(): RepoFragment
}