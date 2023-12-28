package com.factorial.domain.usecase

import com.factorial.domain.model.list.FoodListModel
import com.factorial.domain.repository.FoodRepository

interface GetFoodListUseCase {

    suspend operator fun invoke(): Result<FoodListModel>

    class Base(
        private val foodRepository: FoodRepository,
    ) : GetFoodListUseCase {

        override suspend operator fun invoke(): Result<FoodListModel> =
            foodRepository.getFoodList()
    }

}