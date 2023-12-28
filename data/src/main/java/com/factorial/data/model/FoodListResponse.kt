package com.factorial.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FoodListResponse(
    @Json(name = "title") val title: String? = null,
    @Json(name = "items") val items: List<FoodApiModel> = emptyList(),
) {

    @JsonClass(generateAdapter = true)
    data class FoodApiModel(
        @Json(name = "id") val id: String,
        @Json(name = "name") val name: String? = null,
        @Json(name = "image") val image: String? = null,
        @Json(name = "color") val color: String? = null,
    )

}