package com.fpr0001.nasaimages.di.modules

import com.fpr0001.nasaimages.search.SearchActivity
import com.fpr0001.nasaimages.search.SearchModule
import com.fpr0001.nasaimages.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesBindingModule {

    @ContributesAndroidInjector(modules = [SearchModule::class])
    abstract fun bindSearch(): SearchActivity

//    @ContributesAndroidInjector(modules = [SplashModule::class])
//    abstract fun bindSplash(): SplashActivity
}