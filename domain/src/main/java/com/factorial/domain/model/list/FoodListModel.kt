package com.factorial.domain.model.list

interface FoodListModel {
    val title: String
    val items: List<FoodModel>

    class Base(
        override val title: String,
        override val items: List<FoodModel>,
    ) : FoodListModel
}
