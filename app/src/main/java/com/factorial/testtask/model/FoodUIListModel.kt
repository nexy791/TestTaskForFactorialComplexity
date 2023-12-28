package com.factorial.testtask.model

import android.os.Parcelable
import com.factorial.common.ext.ColorExt.Companion.toColorOrBlack
import com.factorial.domain.model.list.FoodListModel
import com.factorial.domain.model.list.FoodModel
import com.factorial.testtask.model.FoodUIListModel.FoodUIItemModel.Companion.toUiModel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

// Model for UI layer
data class FoodUIListModel(
    override val title: String,
    override val items: List<FoodUIItemModel>,
) : FoodListModel {

    companion object {

        fun FoodListModel.toUiModel(): FoodUIListModel =
            FoodUIListModel(
                title = title,
                items = items.toUiModel()
            )

    }

    // to pass all data to InfoFragment
    // cuz i can`t pass only id and get data from db or api
    @Parcelize
    data class FoodUIItemModel(
        private val foodModel: @RawValue FoodModel,
        val colorHex: Int,
    ) : FoodModel by foodModel, Parcelable {

        companion object {

            fun FoodModel.toUiModel(): FoodUIItemModel =
                FoodUIItemModel(
                    foodModel = this,
                    colorHex = color.toColorOrBlack()
                )

            fun List<FoodModel>.toUiModel(): List<FoodUIItemModel> =
                map { it.toUiModel() }

        }
    }


}
