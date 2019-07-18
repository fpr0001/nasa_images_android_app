package com.fpr0001.nasaimages

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.fpr0001.nasaimages.apis.NasaApi
import com.fpr0001.nasaimages.search.*
import com.fpr0001.nasaimages.utils.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import org.mockito.Mockito.mock
import javax.inject.Singleton


/**
 * ALL FUNCTIONS MUST BE OPEN, SO MOCKITO CAN ACCESS THEM.
 * ALL DEPENDENCIES MUST BE DECLARED HERE, BECAUSE DAGGERMOCK ONLY
 * REPLACES INSTANCES FROM APPLICATION COMPONENT
 */
@Module
open class AppModuleForTest {

    @Provides
    @Singleton
    open fun providesSchedulerProviderImpl(): SchedulerProviderTestImpl = SchedulerProviderTestImpl()

    @Provides
    @Singleton
    open fun providesSchedulerProvider(impl: SchedulerProviderTestImpl): SchedulerProvider = impl

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
    internal fun providesSearchAdapterImpl(glide: RequestManager): SearchAdapterImpl = SearchAdapterImpl(glide)


    @Provides
    @Singleton
    internal fun providesSearchAdapter(impl: SearchAdapterImpl): SearchAdapter = impl

    @Provides
    @Singleton
    internal fun providesGlide(app: Application) = Glide.with(app)


    @Provides
    @Singleton
    open fun providesRepository(impl: ResponseRepositoryImpl): ResponseRepository =
        impl

    @Provides
    @Singleton
    open fun providesRepositoryImpl(): ResponseRepositoryImpl = mock(ResponseRepositoryImpl::class.java)

    @Provides
    open fun providesResponseMapper() = ResponseMapper()

}