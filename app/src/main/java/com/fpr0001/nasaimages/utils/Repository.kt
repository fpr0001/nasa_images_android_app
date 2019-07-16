package com.fpr0001.nasaimages.utils

import android.os.Bundle
import android.os.Parcelable
import com.fpr0001.nasaimages.apis.NasaApi
import com.fpr0001.nasaimages.models.ImageData
import com.fpr0001.nasaimages.models.ItemResponse
import com.fpr0001.nasaimages.models.LinkResponse
import com.fpr0001.nasaimages.models.NasaResponse
import com.fpr0001.nasaimages.search.SearchActivity
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.toObservable
import kotlinx.android.synthetic.main.activity_search.*
import java.lang.RuntimeException

open class ResponseRepositoryImpl(
    private val nasaApi: NasaApi,
    private val mapper: ResponseMapper
) : ResponseRepository {

    override var cachedImageDataList = mutableListOf<ImageData>()

    override fun fetchImages(page: Int, query: String): Single<List<ImageData>> {
        return nasaApi.search(query, page)
            .map { it.collection?.items ?: throw RuntimeException("No items") }
            .map { list -> list.mapNotNull { obj -> mapper.fromItemResponse(obj) } }
    }
}

interface ResponseRepository {
    fun fetchImages(page: Int, query: String): Single<List<ImageData>>
    var cachedImageDataList: MutableList<ImageData>
}

class ResponseMapper {

    fun fromItemResponse(obj: ItemResponse): ImageData? {

        val url = obj.links?.firstOrNull()?.href ?: return null
        val auxObjData = obj.data?.firstOrNull() ?: return null
        val imageData = ImageData(url = url)
        imageData.title = auxObjData.title
        imageData.description = auxObjData.description
        imageData.dateCreated = auxObjData.dateCreated

        return imageData
    }
}