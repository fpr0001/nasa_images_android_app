package com.fpr0001.nasaimages.di

import com.fpr0001.nasaimages.AppForTests
import com.fpr0001.nasaimages.detail.DetailActivity
import com.fpr0001.nasaimages.detail.DetailActivityTest
import com.fpr0001.nasaimages.di.modules.*
import com.fpr0001.nasaimages.search.SearchActivityTest
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
        AppModuleForTest::class
    ]
)
interface AppComponentForTest : AndroidInjector<AppForTests> {

    fun inject(test: SearchActivityTest)

    fun inject(test: DetailActivityTest)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun provideApplication(app: AppForTests): Builder

        fun build(): AppComponentForTest
    }
}