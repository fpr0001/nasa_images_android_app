package com.fpr0001.nasaimages.di.modules

import android.net.Uri
import com.fpr0001.nasaimages.BuildConfig
import com.fpr0001.nasaimages.R
import com.fpr0001.nasaimages.apis.NasaApi
import com.fpr0001.nasaimages.models.ItemResponse
import com.fpr0001.nasaimages.models.NasaResponse
import com.fpr0001.nasaimages.search.SearchPresenterImpl.Companion.MAX_COUNT_PER_PAGE
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

    private val drawableLocalUrl =
        Uri.parse("android.resource://${BuildConfig.APPLICATION_ID}/" + R.mipmap.ic_launcher_foreground)
    override val title = "Proposed Missions - Terrestrial Planet Finder"

    private val json =
        "{\"collection\":{\"metadata\":{\"total_hits\":5706},\"href\":\"https://images-api.nasa.gov/search?media_type=image&q=planets\",\"links\":[{\"rel\":\"next\",\"prompt\":\"Next\",\"href\":\"https://images-api.nasa.gov/search?page=2&q=planets&media_type=image\"}],\"version\":\"1.0\",\"items\":[{\"href\":\"https://images-assets.nasa.gov/image/PIA04499/collection.json\",\"links\":[{\"rel\":\"preview\",\"href\":\"$drawableLocalUrl\",\"render\":\"image\"}],\"data\":[{\"description_508\":\"NASA Terrestrial Planet Finder will use multiple telescopes working together to take family portraits of stars and their orbiting planets and determine which planets may have the right chemistry to sustain life.\",\"center\":\"JPL\",\"secondary_creator\":\"NASA/JPL\",\"title\":\"$title\",\"description\":\"NASA Terrestrial Planet Finder will use multiple telescopes working together to take family portraits of stars and their orbiting planets and determine which planets may have the right chemistry to sustain life.\",\"date_created\":\"2003-06-20T00:02:58Z\",\"keywords\":[\"Terrestrial Planet Finder\"],\"nasa_id\":\"PIA04499\",\"media_type\":\"image\"}]},{\"href\":\"https://images-assets.nasa.gov/image/PIA14801/collection.json\",\"links\":[{\"rel\":\"preview\",\"href\":\"$drawableLocalUrl\",\"render\":\"image\"}],\"data\":[{\"description_508\":\"Sliver of a Planet\",\"center\":\"JPL\",\"secondary_creator\":\"NASA/Johns Hopkins University Applied Physics Laboratory/Carnegie Institution of Washington\",\"title\":\"Sliver of a Planet\",\"description\":\"Sliver of a Planet\",\"date_created\":\"2011-08-25T21:00:54Z\",\"keywords\":[\"Mercury\",\"MESSENGER\"],\"nasa_id\":\"PIA14801\",\"media_type\":\"image\"}]},{\"href\":\"https://images-assets.nasa.gov/image/PIA09784/collection.json\",\"links\":[{\"rel\":\"preview\",\"href\":\"$drawableLocalUrl\",\"render\":\"image\"}],\"data\":[{\"description_508\":\"Planet in Repose\",\"center\":\"JPL\",\"secondary_creator\":\"NASA/JPL/Space Science Institute\",\"title\":\"Planet in Repose\",\"description\":\"Planet in Repose\",\"date_created\":\"2007-12-03T13:50:36Z\",\"keywords\":[\"Saturn\",\"Cassini-Huygens\"],\"nasa_id\":\"PIA09784\",\"media_type\":\"image\"}]}]}}"

    override fun search(query: String, page: Int): Single<NasaResponse> {
        return Single.just(gson.fromJson(json, NasaResponse::class.java).apply {
            val aux = this.collection!!.items!!
            val newList = mutableListOf<ItemResponse>()
            while (newList.size < MAX_COUNT_PER_PAGE) {
                newList.addAll(aux)
            }
            this.collection!!.items = newList.take(MAX_COUNT_PER_PAGE)
        })
    }

}