package com.fpr0001.nasaimages.utils

import com.fpr0001.nasaimages.apis.NasaApi
import com.fpr0001.nasaimages.models.ImageData
import com.fpr0001.nasaimages.models.ItemResponse
import io.reactivex.Single

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

open class ResponseMapper {

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