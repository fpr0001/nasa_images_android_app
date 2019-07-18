package com.fpr0001.nasaimages

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivitiesBindingModuleForTest::class,
        AppModuleForTest::class]
)
interface AppComponentForTest : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        fun appModuleForTest(appModuleForTest: AppModuleForTest): Builder

        @BindsInstance
        fun provideApplication(app: Application): Builder

        fun build(): AppComponentForTest
    }
}