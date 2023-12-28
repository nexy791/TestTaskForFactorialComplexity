package com.factorial.domain.repository

import com.factorial.domain.model.description.FoodDescriptionModel
import com.factorial.domain.model.list.FoodListModel

interface FoodRepository {

    suspend fun getFoodList(): Result<FoodListModel>

    suspend fun getFoodDescriptionById(id: String): Result<FoodDescriptionModel>

}