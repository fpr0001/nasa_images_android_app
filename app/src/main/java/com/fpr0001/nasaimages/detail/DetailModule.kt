package com.fpr0001.nasaimages.detail

import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.fpr0001.nasaimages.search.SearchActivity
import com.fpr0001.nasaimages.search.SearchAdapter
import com.fpr0001.nasaimages.search.SearchPresenter
import com.fpr0001.nasaimages.utils.ResponseRepository
import com.fpr0001.nasaimages.utils.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
abstract class DetailModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        internal fun providesGlide(activity: DetailActivity) = Glide.with(activity)

    }
}