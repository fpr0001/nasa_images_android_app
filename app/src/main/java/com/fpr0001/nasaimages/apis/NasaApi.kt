package com.fpr0001.nasaimages.apis

import com.fpr0001.nasaimages.models.CollectionResponse
import com.fpr0001.nasaimages.models.NasaResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApi {

    @GET("search?media_type=image")
    fun search(
        @Query("q") query: String,
//                  @Query("description") description: String?,
//                  @Query("keywords") keywords: String?,
//                  @Query("media_type") searchQuery: String?,
        @Query("page") page: Int = 1
    )
            : Single<NasaResponse>
}