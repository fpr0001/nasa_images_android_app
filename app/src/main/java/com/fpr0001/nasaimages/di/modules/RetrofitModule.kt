package com.fpr0001.nasaimages.di.modules

import com.fpr0001.nasaimages.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Module
    companion object {

        @Singleton
        @Provides
        @JvmStatic
        internal fun providesOkHttpClient(): OkHttpClient {
            val builder = OkHttpClient()
                .newBuilder()
            if (BuildConfig.DEBUG) {
                builder.addNetworkInterceptor(providesLogginInterceptor())
            }
            return builder.build()
        }

        @Singleton
        @Provides
        @JvmStatic
        internal fun providesLogginInterceptor(): HttpLoggingInterceptor {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return loggingInterceptor
        }

        @Singleton
        @Provides
        @JvmStatic
        internal fun providesGsonConverterFactory(gson: Gson): GsonConverterFactory {
            return GsonConverterFactory.create(gson)
        }

        @Singleton
        @Provides
        @JvmStatic
        internal fun providesGson(): Gson {
            val gsonBuilder = GsonBuilder()
            return gsonBuilder
                .create()
        }

        @Singleton
        @Provides
        @JvmStatic
        internal fun providesAdapter(): RxJava2CallAdapterFactory {
            return RxJava2CallAdapterFactory.create()
        }

        @Singleton
        @Provides
        @JvmStatic
        internal fun providesRetrofit(okHttpClient: OkHttpClient,
                                      gsonConverterFactory: GsonConverterFactory,
                                      adapterFactory: RxJava2CallAdapterFactory
        ): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(adapterFactory)
                .client(okHttpClient)
                .build()
        }

        private const val BASE_URL = "https://images-api.nasa.gov"
    }
}