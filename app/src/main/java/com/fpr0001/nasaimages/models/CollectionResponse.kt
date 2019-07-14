package com.fpr0001.nasaimages.models

import com.google.gson.annotations.SerializedName

class NasaResponse {

    @SerializedName("collection")
    var collection: CollectionResponse? = null

}

class CollectionResponse {

    @SerializedName("version")
    var version: String? = null

    @SerializedName("metadata")
    var metadata: MetadataResponse? = null

    @SerializedName("items")
    var items: List<ItemResponse>? = null

    @SerializedName("href")
    var href: String? = null

    @SerializedName("links")
    var links: List<LinkResponse>? = null
}

class MetadataResponse {

    @SerializedName("total_hits")
    var totalHits: Int? = null

}

class LinkResponse {

    @SerializedName("rel")
    var rel: String? = null

    @SerializedName("prompt")
    var prompt: String? = null

    @SerializedName("href")
    var href: String? = null
}