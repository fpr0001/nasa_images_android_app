package com.fpr0001.nasaimages.search

import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.fpr0001.nasaimages.utils.ResponseRepository
import com.fpr0001.nasaimages.utils.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
open class SearchModuleForTests {

    @Provides
    open fun providesPresenter(impl: SearchPresenterImpl): SearchPresenter = impl

    @Provides
    open fun providesPresenterImpl(
        repository: ResponseRepository,
        adapter: SearchAdapter,
        schedulerProvider: SchedulerProvider
    ): SearchPresenterImpl {
        return SearchPresenterImpl(repository, schedulerProvider, adapter)
    }

    @Provides
    internal fun providesSearchAdapterImpl(glide: RequestManager): SearchAdapterImpl = SearchAdapterImpl(glide)


    @Provides
    internal fun providesSearchAdapter(impl: SearchAdapterImpl): SearchAdapter = impl

    @Provides
    internal fun providesGlide(activity: SearchActivity) = Glide.with(activity)

}