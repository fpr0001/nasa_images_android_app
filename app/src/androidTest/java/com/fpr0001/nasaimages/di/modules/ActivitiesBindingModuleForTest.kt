package com.fpr0001.nasaimages.di.modules

import com.fpr0001.nasaimages.search.SearchActivity
import com.fpr0001.nasaimages.search.SearchModuleForTests
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesBindingModuleForTest {

    @ContributesAndroidInjector(modules = [SearchModuleForTests::class])
    abstract fun bindSearch(): SearchActivity
//
//    @ContributesAndroidInjector(modules = [SearchModuleForTests::class])
//    abstract fun bindSearchTest(): SearchModuleForTests
}