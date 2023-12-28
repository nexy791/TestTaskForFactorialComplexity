package com.factorial.domain.usecase

import com.factorial.domain.model.description.FoodDescriptionModel
import com.factorial.domain.repository.FoodRepository

interface GetFoodDescriptionByIdUseCase {

    suspend operator fun invoke(id: String): Result<FoodDescriptionModel>

    class Base(private val foodRepository: FoodRepository) : GetFoodDescriptionByIdUseCase {

        override suspend operator fun invoke(id: String): Result<FoodDescriptionModel> =
            foodRepository.getFoodDescriptionById(id)
    }

}