package com.factorial.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FoodDescriptionResponse(
    // @Json(name = "id") val id: String,
    // I don't need this field
    @Json(name = "text") val description: String? = null,
)