package com.factorial.domain.model.description

interface FoodDescriptionModel {
    val description: String

    class Base(
        override val description: String,
    ) : FoodDescriptionModel
}