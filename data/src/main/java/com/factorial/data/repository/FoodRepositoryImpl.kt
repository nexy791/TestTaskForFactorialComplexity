package com.factorial.data.repository

import com.factorial.data.mapper.Mapper
import com.factorial.data.model.FoodDescriptionResponse
import com.factorial.data.model.FoodListResponse
import com.factorial.data.source.FoodRemoteDataSource
import com.factorial.domain.model.description.FoodDescriptionModel
import com.factorial.domain.model.list.FoodListModel
import com.factorial.domain.repository.FoodRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class FoodRepositoryImpl(
    private val dispatcher: CoroutineDispatcher,
    private val foodRemoteDataSource: FoodRemoteDataSource,
    private val foodMapper: Mapper<FoodListResponse, FoodListModel>,
    private val foodDescriptionMapper: Mapper<FoodDescriptionResponse, FoodDescriptionModel>,
) : FoodRepository {

    override suspend fun getFoodList(): Result<FoodListModel> = withContext(dispatcher) {
        val result = foodRemoteDataSource.getFoodList()
        result.mapCatching { foodMapper.map(it) }
    }

    override suspend fun getFoodDescriptionById(id: String): Result<FoodDescriptionModel> =
        withContext(dispatcher) {
            val result = foodRemoteDataSource.getFoodById(id)
            result.mapCatching { foodDescriptionMapper.map(it) }
        }
}