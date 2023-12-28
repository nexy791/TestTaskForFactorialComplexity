package com.factorial.data.source

import com.factorial.data.model.FoodDescriptionResponse
import com.factorial.data.model.FoodListResponse

interface FoodRemoteDataSource {
    suspend fun getFoodList(): Result<FoodListResponse>
    suspend fun getFoodById(id: String): Result<FoodDescriptionResponse>
}