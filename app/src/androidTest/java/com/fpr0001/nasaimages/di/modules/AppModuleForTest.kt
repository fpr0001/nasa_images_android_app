package com.fpr0001.nasaimages.di.modules

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.fpr0001.nasaimages.AppForTests
import com.fpr0001.nasaimages.search.*
import com.fpr0001.nasaimages.utils.*
import dagger.Module
import dagger.Provides
import org.mockito.Mockito.mock
import javax.inject.Singleton


/**
 * ALL FUNCTIONS MUST BE OPEN, SO MOCKITO CAN ACCESS THEM.
 * ALL DEPENDENCIES MUST BE DECLARED HERE, BECAUSE DAGGERMOCK ONLY
 * REPLACES INSTANCES FROM APPLICATION COMPONENT
 */
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
    internal fun providesSearchAdapterImpl(glide: RequestManager): SearchAdapterImpl = SearchAdapterImpl(glide)


    @Provides
    @Singleton
    internal fun providesSearchAdapter(impl: SearchAdapterImpl): SearchAdapter = impl

}