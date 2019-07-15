package com.fpr0001.nasaimages.di.components

import android.app.Application
import com.fpr0001.nasaimages.App
import com.fpr0001.nasaimages.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        UtilsModule::class,
        RepositoryModule::class,
        RetrofitModule::class,
        ApiModule::class,
        AndroidInjectionModule::class,
        ActivitiesBindingModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun provideApplication(app: Application): Builder

        fun build(): AppComponent
    }
}