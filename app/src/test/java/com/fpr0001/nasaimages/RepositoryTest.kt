package com.fpr0001.nasaimages

import com.fpr0001.nasaimages.apis.NasaApi
import com.fpr0001.nasaimages.models.NasaResponse
import com.fpr0001.nasaimages.utils.ResponseMapper
import com.fpr0001.nasaimages.utils.ResponseRepository
import com.fpr0001.nasaimages.utils.ResponseRepositoryImpl
import com.google.gson.Gson
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class RepositoryTest {

    private val json =
        "{\"collection\":{\"metadata\":{\"total_hits\":5706},\"href\":\"https://images-api.nasa.gov/search?media_type=image&q=planets\",\"links\":[{\"rel\":\"next\",\"prompt\":\"Next\",\"href\":\"https://images-api.nasa.gov/search?page=2&q=planets&media_type=image\"}],\"version\":\"1.0\",\"items\":[{\"href\":\"https://images-assets.nasa.gov/image/PIA04499/collection.json\",\"links\":[{\"rel\":\"preview\",\"href\":\"https://images-assets.nasa.gov/image/PIA04499/PIA04499~thumb.jpg\",\"render\":\"image\"}],\"data\":[{\"description_508\":\"NASA Terrestrial Planet Finder will use multiple telescopes working together to take family portraits of stars and their orbiting planets and determine which planets may have the right chemistry to sustain life.\",\"center\":\"JPL\",\"secondary_creator\":\"NASA/JPL\",\"title\":\"Proposed Missions - Terrestrial Planet Finder\",\"description\":\"NASA Terrestrial Planet Finder will use multiple telescopes working together to take family portraits of stars and their orbiting planets and determine which planets may have the right chemistry to sustain life.\",\"date_created\":\"2003-06-20T00:02:58Z\",\"keywords\":[\"Terrestrial Planet Finder\"],\"nasa_id\":\"PIA04499\",\"media_type\":\"image\"}]},{\"href\":\"https://images-assets.nasa.gov/image/PIA14801/collection.json\",\"links\":[{\"rel\":\"preview\",\"href\":\"https://images-assets.nasa.gov/image/PIA14801/PIA14801~thumb.jpg\",\"render\":\"image\"}],\"data\":[{\"description_508\":\"Sliver of a Planet\",\"center\":\"JPL\",\"secondary_creator\":\"NASA/Johns Hopkins University Applied Physics Laboratory/Carnegie Institution of Washington\",\"title\":\"Sliver of a Planet\",\"description\":\"Sliver of a Planet\",\"date_created\":\"2011-08-25T21:00:54Z\",\"keywords\":[\"Mercury\",\"MESSENGER\"],\"nasa_id\":\"PIA14801\",\"media_type\":\"image\"}]},{\"href\":\"https://images-assets.nasa.gov/image/PIA09784/collection.json\",\"links\":[{\"rel\":\"preview\",\"href\":\"https://images-assets.nasa.gov/image/PIA09784/PIA09784~thumb.jpg\",\"render\":\"image\"}],\"data\":[{\"description_508\":\"Planet in Repose\",\"center\":\"JPL\",\"secondary_creator\":\"NASA/JPL/Space Science Institute\",\"title\":\"Planet in Repose\",\"description\":\"Planet in Repose\",\"date_created\":\"2007-12-03T13:50:36Z\",\"keywords\":[\"Saturn\",\"Cassini-Huygens\"],\"nasa_id\":\"PIA09784\",\"media_type\":\"image\"}]}]}}"

    private val gson = Gson()

    lateinit var nasaResponse: NasaResponse

    private val mapper = ResponseMapper()

    @Mock
    lateinit var nasaApi: NasaApi

    lateinit var repository: ResponseRepository

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        repository = ResponseRepositoryImpl(nasaApi, mapper)
        nasaResponse = gson.fromJson(json, NasaResponse::class.java)
    }

    @Test
    fun shouldReturnErrorWhenCollectionIsNull() {
        nasaResponse.collection = null
        Mockito.`when`(nasaApi.search(anyString(), anyInt())).thenReturn(Single.just(nasaResponse))
        repository.fetchImages(1, "").test().assertError { e -> e is RuntimeException }.dispose()
    }

    @Test
    fun shouldReturnSingleWhenCollectionIsNotNull() {
        Mockito.`when`(nasaApi.search(anyString(), anyInt())).thenReturn(Single.just(nasaResponse))
        repository.fetchImages(1, "").test().assertValue { list -> list.size == 3 }.dispose()
    }

    @Test
    fun shouldMapFromItemResponseToImageData_emptyLists() {
        nasaResponse.collection!!.items!![0].links = emptyList()
        nasaResponse.collection!!.items!![1].data = emptyList()
        Mockito.`when`(nasaApi.search(anyString(), anyInt())).thenReturn(Single.just(nasaResponse))
        repository.fetchImages(1, "").test().assertValue { a -> a.size == 1 }.dispose()
    }

    @Test
    fun shouldMapFromItemResponseToImageData_nullValues() {
        nasaResponse.collection!!.items!![0].links = null
        nasaResponse.collection!!.items!![1].data = null
        Mockito.`when`(nasaApi.search(anyString(), anyInt())).thenReturn(Single.just(nasaResponse))
        repository.fetchImages(1, "").test().assertValue { a -> a.size == 1 }.dispose()
    }
}