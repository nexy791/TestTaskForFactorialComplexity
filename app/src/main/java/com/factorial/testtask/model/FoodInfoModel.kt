package com.factorial.testtask.model

import com.factorial.domain.model.list.FoodModel

data class FoodInfoModel(
    val foodUIItemModel: FoodUIListModel.FoodUIItemModel,
    val description: String,
) : FoodModel by foodUIItemModel {

    companion object {

        fun from(
            foodUIItemModel: FoodUIListModel.FoodUIItemModel,
            description: String,
        ): FoodInfoModel {
            return FoodInfoModel(
                foodUIItemModel = foodUIItemModel,
                description = description,
            )
        }
    }

}