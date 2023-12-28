package com.factorial.data.mapper

import com.factorial.data.model.FoodListResponse
import com.factorial.domain.model.list.FoodListModel
import com.factorial.domain.model.list.FoodModel

class FoodListApiMapper : BaseMapper<FoodListResponse, FoodListModel>() {

    override fun map(input: FoodListResponse): FoodListModel {
        return FoodListModel.Base(
            title = input.title.orEmpty(),
            items = input.items.map {
                FoodModel.Base(
                    id = it.id,
                    name = it.name.orEmpty(),
                    image = buildImageUrl(it.image),
                    color = it.color.orEmpty(),
                )
            },
        )
    }

}