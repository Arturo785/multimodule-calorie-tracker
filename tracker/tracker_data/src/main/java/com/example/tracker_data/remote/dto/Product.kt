package com.example.tracker_data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Product(
    // this fields are the same as the given name in the back-end but in moshi instead of Gson

    @field:Json(name = "image_front_thumb_url")
    val imageFrontThumbUrl: String?,
    val nutriments: Nutriments,
    @field:Json(name = "product_name")
    val productName: String?
)