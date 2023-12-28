package com.factorial.data.source

import com.factorial.common.ext.NetworkExt.Companion.safeRequest
import com.factorial.data.model.FoodDescriptionResponse
import com.factorial.data.model.FoodListResponse
import com.factorial.data.service.ApiService

class FoodRemoteDataSourceImpl(
    private val apiService: ApiService,
) : FoodRemoteDataSource {

    override suspend fun getFoodList(): Result<FoodListResponse> = safeRequest {
        apiService.getFoodList()
    }

    override suspend fun getFoodById(id: String): Result<FoodDescriptionResponse> = safeRequest {
        apiService.getFoodById(id)
    }
}
