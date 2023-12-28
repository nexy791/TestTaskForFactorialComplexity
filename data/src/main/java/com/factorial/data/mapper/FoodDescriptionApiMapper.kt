package com.factorial.data.mapper

import com.factorial.data.model.FoodDescriptionResponse
import com.factorial.domain.model.description.FoodDescriptionModel

class FoodDescriptionApiMapper : BaseMapper<FoodDescriptionResponse, FoodDescriptionModel>() {

    override fun map(input: FoodDescriptionResponse): FoodDescriptionModel {
        return FoodDescriptionModel.Base(
            description = input.description.orEmpty(),
        )
    }

}