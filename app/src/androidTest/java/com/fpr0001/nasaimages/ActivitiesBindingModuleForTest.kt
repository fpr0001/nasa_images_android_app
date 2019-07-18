package com.fpr0001.nasaimages

import com.fpr0001.nasaimages.search.SearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesBindingModuleForTest {

    @ContributesAndroidInjector
    abstract fun bindSearch(): SearchActivity
}