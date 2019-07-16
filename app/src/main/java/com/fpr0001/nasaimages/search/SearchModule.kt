package com.fpr0001.nasaimages.search

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.fpr0001.nasaimages.utils.ResponseRepository
import com.fpr0001.nasaimages.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
abstract class SearchModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        internal fun providesPresenter(
            repository: ResponseRepository,
            adapter: SearchAdapter,
            schedulerProvider: SchedulerProvider
        ): SearchPresenter {
            return SearchPresenter(repository, schedulerProvider, adapter)
        }

        @JvmStatic
        @Provides
        internal fun providesSearchAdapter(glide: RequestManager) = SearchAdapter(glide)

        @JvmStatic
        @Provides
        internal fun providesGlide(activity: SearchActivity) = Glide.with(activity)

    }
}