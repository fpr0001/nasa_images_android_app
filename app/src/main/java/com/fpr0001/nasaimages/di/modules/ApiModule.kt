package com.fpr0001.nasaimages.di.modules

import com.fpr0001.nasaimages.apis.NasaApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit

@Module
class ApiModule {

    @Module
    companion object {

        @Provides
        @Reusable
        @JvmStatic
        internal fun providesNasaApi(retrofit: Retrofit): NasaApi {
            return retrofit.create(NasaApi::class.java)
        }
    }
}