package com.fpr0001.nasaimages.di.modules

import com.fpr0001.nasaimages.apis.NasaApi
import com.fpr0001.nasaimages.models.NasaResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import javax.inject.Singleton


@Module
open class ApiModuleForTests {

    @Provides
    @Singleton
    open fun providesNasaApi(gson: Gson): NasaApi {
        return NasaApiImplForTests(gson)
    }

    @Singleton
    @Provides
    open fun providesGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder
            .create()
    }
}

class NasaApiImplForTests(private val gson: Gson) : NasaApi {

    private val json =
        "{\"collection\":{\"metadata\":{\"total_hits\":5706},\"href\":\"https://images-api.nasa.gov/search?media_type=image&q=planets\",\"links\":[{\"rel\":\"next\",\"prompt\":\"Next\",\"href\":\"https://images-api.nasa.gov/search?page=2&q=planets&media_type=image\"}],\"version\":\"1.0\",\"items\":[{\"href\":\"https://images-assets.nasa.gov/image/PIA04499/collection.json\",\"links\":[{\"rel\":\"preview\",\"href\":\"https://images-assets.nasa.gov/image/PIA04499/PIA04499~thumb.jpg\",\"render\":\"image\"}],\"data\":[{\"description_508\":\"NASA Terrestrial Planet Finder will use multiple telescopes working together to take family portraits of stars and their orbiting planets and determine which planets may have the right chemistry to sustain life.\",\"center\":\"JPL\",\"secondary_creator\":\"NASA/JPL\",\"title\":\"Proposed Missions - Terrestrial Planet Finder\",\"description\":\"NASA Terrestrial Planet Finder will use multiple telescopes working together to take family portraits of stars and their orbiting planets and determine which planets may have the right chemistry to sustain life.\",\"date_created\":\"2003-06-20T00:02:58Z\",\"keywords\":[\"Terrestrial Planet Finder\"],\"nasa_id\":\"PIA04499\",\"media_type\":\"image\"}]},{\"href\":\"https://images-assets.nasa.gov/image/PIA14801/collection.json\",\"links\":[{\"rel\":\"preview\",\"href\":\"https://images-assets.nasa.gov/image/PIA14801/PIA14801~thumb.jpg\",\"render\":\"image\"}],\"data\":[{\"description_508\":\"Sliver of a Planet\",\"center\":\"JPL\",\"secondary_creator\":\"NASA/Johns Hopkins University Applied Physics Laboratory/Carnegie Institution of Washington\",\"title\":\"Sliver of a Planet\",\"description\":\"Sliver of a Planet\",\"date_created\":\"2011-08-25T21:00:54Z\",\"keywords\":[\"Mercury\",\"MESSENGER\"],\"nasa_id\":\"PIA14801\",\"media_type\":\"image\"}]},{\"href\":\"https://images-assets.nasa.gov/image/PIA09784/collection.json\",\"links\":[{\"rel\":\"preview\",\"href\":\"https://images-assets.nasa.gov/image/PIA09784/PIA09784~thumb.jpg\",\"render\":\"image\"}],\"data\":[{\"description_508\":\"Planet in Repose\",\"center\":\"JPL\",\"secondary_creator\":\"NASA/JPL/Space Science Institute\",\"title\":\"Planet in Repose\",\"description\":\"Planet in Repose\",\"date_created\":\"2007-12-03T13:50:36Z\",\"keywords\":[\"Saturn\",\"Cassini-Huygens\"],\"nasa_id\":\"PIA09784\",\"media_type\":\"image\"}]}]}}"

    override fun search(query: String, page: Int): Single<NasaResponse> {
        return Single.just(gson.fromJson(json, NasaResponse::class.java))
    }

}