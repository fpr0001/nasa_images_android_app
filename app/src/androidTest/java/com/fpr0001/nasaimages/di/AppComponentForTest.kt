package com.fpr0001.nasaimages.di

import android.app.Application
import com.fpr0001.nasaimages.AppForTests
import com.fpr0001.nasaimages.di.modules.ActivitiesBindingModuleForTest
import com.fpr0001.nasaimages.di.modules.ApiModuleForTests
import com.fpr0001.nasaimages.di.modules.RepositoryModuleForTests
import com.fpr0001.nasaimages.di.modules.UtilsModuleForTests
import com.fpr0001.nasaimages.search.Banana
import com.fpr0001.nasaimages.search.SearchActivityTest2
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
        UtilsModuleForTests::class,
        ApiModuleForTests::class,
        RepositoryModuleForTests::class]
)
interface AppComponentForTest : AndroidInjector<AppForTests> {

    fun a(a: Banana)

    fun ab(a: SearchActivityTest2)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun provideApplication(app: Application): Builder

        fun build(): AppComponentForTest
    }
}