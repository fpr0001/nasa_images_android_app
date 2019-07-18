package com.fpr0001.nasaimages.di.modules

import com.fpr0001.nasaimages.apis.NasaApi
import com.fpr0001.nasaimages.utils.ResponseMapper
import com.fpr0001.nasaimages.utils.ResponseRepository
import com.fpr0001.nasaimages.utils.ResponseRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class RepositoryModuleForTests {

    @Provides
    @Singleton
    open fun providesRepository(impl: ResponseRepositoryImpl): ResponseRepository =
        impl

    @Provides
    @Singleton
    open fun providesRepositoryImpl(nasaApi: NasaApi, mapper: ResponseMapper): ResponseRepositoryImpl =
        ResponseRepositoryImpl(nasaApi, mapper)

    @Provides
    @Singleton
    open fun providesResponseMapper() = ResponseMapper()
}