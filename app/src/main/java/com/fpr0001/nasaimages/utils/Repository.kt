package com.fpr0001.nasaimages.utils

import com.fpr0001.nasaimages.apis.NasaApi
import com.fpr0001.nasaimages.models.LinkResponse
import com.fpr0001.nasaimages.models.NasaResponse
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.toObservable
import java.lang.RuntimeException

open class ResponseRepositoryImpl(private val nasaApi: NasaApi) : ResponseRepository {

    override fun fetchImages(page: Int, query: String): Single<List<LinkResponse>> {
        return nasaApi.search(query, page)
            .map { it.collection?.items ?: throw RuntimeException("No items") } //return list of items
            .map { it.filter { item -> item.links?.firstOrNull() != null }} //return list of links
            .flatMapObservable { list -> list.toObservable()}
            .map { obj -> obj.links!!.first() }
            .toList()
    }
}

interface ResponseRepository {

    fun fetchImages(page: Int, query: String): Single<List<LinkResponse>>

}