package com.fpr0001.nasaimages.apis

import androidx.annotation.VisibleForTesting
import com.fpr0001.nasaimages.models.NasaResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApi {

    @GET("search?media_type=image")
    fun search(
        @Query("q") query: String,
        @Query("page") page: Int = 1
    )
            : Single<NasaResponse>

    @VisibleForTesting
    val title: String
}