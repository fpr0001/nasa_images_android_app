package com.fpr0001.nasaimages

import com.fpr0001.nasaimages.di.AppComponentForTest
import com.fpr0001.nasaimages.di.DaggerAppComponentForTest
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class AppForTests : DaggerApplication() {

    lateinit var component: AppComponentForTest

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponentForTest
            .builder()
            .provideApplication(this)
            .build()
            .also { component = it }
    }
}