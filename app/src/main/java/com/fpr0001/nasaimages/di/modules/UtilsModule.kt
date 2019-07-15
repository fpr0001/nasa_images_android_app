package com.fpr0001.nasaimages.di.modules

import com.fpr0001.nasaimages.utils.SchedulerProvider
import com.fpr0001.nasaimages.utils.SchedulerProviderImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class UtilsModule {

    @Singleton
    @Binds
    abstract fun bindsSchedulerProvider(schedulerProviderImpl: SchedulerProviderImpl): SchedulerProvider



}