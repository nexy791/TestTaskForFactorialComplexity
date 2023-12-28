package com.factorial.domain.model.list

interface FoodModel {
    val id: String
    val name: String
    val image: String?
    val color: String

    class Base(
        override val id: String,
        override val name: String,
        override val image: String?,
        override val color: String,
    ) : FoodModel

}