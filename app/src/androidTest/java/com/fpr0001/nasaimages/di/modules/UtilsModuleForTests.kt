package com.fpr0001.nasaimages.di.modules

import com.fpr0001.nasaimages.utils.SchedulerProvider
import com.fpr0001.nasaimages.utils.SchedulerProviderTestImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class UtilsModuleForTests {

    @Singleton
    @Provides
    open fun providesSchedulerProvider(schedulerProviderImpl: SchedulerProviderTestImpl): SchedulerProvider =
        schedulerProviderImpl

}