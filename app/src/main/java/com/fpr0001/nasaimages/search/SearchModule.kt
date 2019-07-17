package com.fpr0001.nasaimages.search

import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.fpr0001.nasaimages.utils.ResponseRepository
import com.fpr0001.nasaimages.utils.SchedulerProvider
import dagger.Module
import dagger.Provides

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
            return SearchPresenterImpl(repository, schedulerProvider, adapter)
        }

        @JvmStatic
        @Provides
        internal fun providesSearchAdapter(glide: RequestManager): SearchAdapter = SearchAdapterImpl(glide)

        @JvmStatic
        @Provides
        internal fun providesGlide(activity: SearchActivity) = Glide.with(activity)

    }
}