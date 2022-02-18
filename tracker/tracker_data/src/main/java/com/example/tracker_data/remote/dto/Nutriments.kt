package com.example.tracker_data.remote.dto

import com.squareup.moshi.Json

// this fields are the same as the given name in the back-end but in moshi instead of Gson

data class Nutriments(
    @field:Json(name = "carbohydrates_100g")
    val carbohydrates100g: Double,
    @field:Json(name = "energy-kcal_100g")
    val energyKcal100g: Double,
    @field:Json(name = "fat_100g")
    val fat100g: Double,
    @field:Json(name = "proteins_100g")
    val proteins100g: Double
)