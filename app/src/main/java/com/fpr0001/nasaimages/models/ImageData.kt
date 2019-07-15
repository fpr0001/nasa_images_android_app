package com.fpr0001.nasaimages.models

import com.google.gson.annotations.SerializedName
import java.util.*

class ImageData {

    @SerializedName("title")
    var title: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("date_created")
    var dateCreated: Date? = null

    @SerializedName("href")
    lateinit var url: String

}