package com.fpr0001.nasaimages.di.modules

import com.bumptech.glide.Glide
import com.fpr0001.nasaimages.AppForTests
import com.fpr0001.nasaimages.utils.SchedulerProvider
import com.fpr0001.nasaimages.utils.SchedulerProviderTestImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class UtilsModuleForTests {

    @Singleton
    @Binds
    abstract fun bindsSchedulerProvider(impl: SchedulerProviderTestImpl): SchedulerProvider

}