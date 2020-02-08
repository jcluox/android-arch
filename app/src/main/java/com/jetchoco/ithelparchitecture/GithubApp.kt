package com.jetchoco.ithelparchitecture

import android.app.Application
import com.jetchoco.ithelparchitecture.di.AppInjector
import com.jetchoco.ithelparchitecture.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

// HasAndroidInjector 故事有點長，請參照：https://dagger.dev/android
class GithubApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)

        AppInjector.init(this)
    }

    override fun androidInjector() = dispatchingAndroidInjector
}