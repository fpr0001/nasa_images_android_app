package com.fpr0001.nasaimages.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.Date

@Parcelize
class ImageData(var title:String? = null,
                var description: String? = null,
                var dateCreated: Date? = null,
                var url: String
                ) : Parcelable