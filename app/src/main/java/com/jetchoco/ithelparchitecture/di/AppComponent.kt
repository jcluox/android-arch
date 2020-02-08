package com.jetchoco.ithelparchitecture.di

import com.jetchoco.ithelparchitecture.GithubApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuildersModule::class,
        FragmentBuildersModule::class,
        NetworkModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        // 給上述的modules(AppModule, BuildersModule的constructor塞進application與provides application)
        @BindsInstance
        fun application(application: GithubApp): Builder

        fun build(): AppComponent
    }

    fun inject(application: GithubApp)
}