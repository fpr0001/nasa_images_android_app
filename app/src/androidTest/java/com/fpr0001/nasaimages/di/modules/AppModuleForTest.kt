package com.fpr0001.nasaimages.di.modules

import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.fpr0001.nasaimages.AppForTests
import com.fpr0001.nasaimages.search.SearchAdapter
import com.fpr0001.nasaimages.search.SearchAdapterImpl
import com.fpr0001.nasaimages.search.SearchPresenter
import com.fpr0001.nasaimages.search.SearchPresenterImpl
import com.fpr0001.nasaimages.utils.ResponseRepository
import com.fpr0001.nasaimages.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import org.mockito.Mockito.spy
import javax.inject.Singleton

@Module(
    includes = [ApiModuleForTests::class,
        RepositoryModuleForTests::class,
        UtilsModuleForTests::class
    ]
)
open class AppModuleForTest {

    @Provides
    @Singleton
    internal fun providesGlide(app: AppForTests) = Glide.with(app)

    @Provides
    @Singleton
    open fun providesPresenter(impl: SearchPresenterImpl): SearchPresenter = impl

    @Provides
    @Singleton
    open fun providesPresenterImpl(
        repository: ResponseRepository,
        adapter: SearchAdapter,
        schedulerProvider: SchedulerProvider
    ): SearchPresenterImpl {
        return SearchPresenterImpl(repository, schedulerProvider, adapter)
    }

    @Provides
    @Singleton
    internal fun providesSearchAdapterImpl(glide: RequestManager): SearchAdapterImpl = spy(SearchAdapterImpl(glide))


    @Provides
    @Singleton
    internal fun providesSearchAdapter(impl: SearchAdapterImpl): SearchAdapter = impl

}