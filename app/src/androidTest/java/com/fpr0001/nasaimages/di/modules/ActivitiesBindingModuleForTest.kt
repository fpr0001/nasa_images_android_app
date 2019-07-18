package com.fpr0001.nasaimages.di.modules

import com.fpr0001.nasaimages.detail.DetailActivity
import com.fpr0001.nasaimages.search.SearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesBindingModuleForTest {

    @ContributesAndroidInjector
    abstract fun bindSearch(): SearchActivity

    @ContributesAndroidInjector
    abstract fun bindDetail(): DetailActivity

}