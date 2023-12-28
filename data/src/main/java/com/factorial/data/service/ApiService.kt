package com.factorial.data.service

import com.factorial.data.model.FoodDescriptionResponse
import com.factorial.data.model.FoodListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("items/random")
    suspend fun getFoodList(): Response<FoodListResponse>

    @GET("texts/{id}")
    suspend fun getFoodById(@Path("id") id: String): Response<FoodDescriptionResponse>

}