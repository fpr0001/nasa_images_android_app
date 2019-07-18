package com.fpr0001.nasaimages

import android.app.Activity
import android.app.Application
import com.fpr0001.nasaimages.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent
            .builder()
            .provideApplication(this)
            .build()
            .inject(this)
    }
}