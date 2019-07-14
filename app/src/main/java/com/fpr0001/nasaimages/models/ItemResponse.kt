package com.fpr0001.nasaimages.models

import com.google.gson.annotations.SerializedName
import java.util.*

class ItemResponse {

    @SerializedName("data")
    var data: List<DataResponse>? = null

    @SerializedName("links")
    var links: List<LinkResponse>? = null

    @SerializedName("href")
    var href: String? = null

}

class DataResponse {

    @SerializedName("center")
    var center: String? = null

    @SerializedName("date_created")
    var dateCreated: Date? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("media_type")
    var mediaType: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("nasa_id")
    var id: String? = null
}