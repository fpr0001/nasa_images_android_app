package com.fpr0001.nasaimages.di.modules

import com.fpr0001.nasaimages.apis.NasaApi
import com.fpr0001.nasaimages.models.ImageData
import com.fpr0001.nasaimages.models.ItemResponse
import com.fpr0001.nasaimages.utils.ResponseMapper
import com.fpr0001.nasaimages.utils.ResponseRepository
import com.fpr0001.nasaimages.utils.ResponseRepositoryImpl
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Singleton

@Module
open class RepositoryModuleForTests {

    @Provides
    @Singleton
    open fun providesRepository(impl: ResponseRepositoryImpl): ResponseRepository =
        impl

    @Provides
    @Singleton
    open fun providesRepositoryImpl(nasaApi: NasaApi, mapper: ResponseMapperForTests): ResponseRepositoryImpl =
        ResponseRepositoryImpl(nasaApi, mapper)

    @Provides
    @Singleton
    open fun providesResponseMapper() = ResponseMapperForTests()

}

open class ResponseMapperForTests : ResponseMapper() {

    override fun fromItemResponse(obj: ItemResponse): ImageData? {
        val response = super.fromItemResponse(obj)
        response?.id = UUID.randomUUID().toString().hashCode().toLong()
        return response
    }
}