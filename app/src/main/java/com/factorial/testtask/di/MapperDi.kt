package com.factorial.testtask.di

import com.factorial.data.mapper.FoodDescriptionApiMapper
import com.factorial.data.mapper.FoodListApiMapper
import com.factorial.data.mapper.Mapper
import com.factorial.data.model.FoodDescriptionResponse
import com.factorial.data.model.FoodListResponse
import com.factorial.domain.model.description.FoodDescriptionModel
import com.factorial.domain.model.list.FoodListModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

// cuz koin does`t understand
val listMapper = named("listMapper")
val descriptionMapper = named("descriptionMapper")

val mapperDi = module {

    factory<Mapper<FoodListResponse, FoodListModel>>(listMapper) {
        FoodListApiMapper()
    }

    factory<Mapper<FoodDescriptionResponse, FoodDescriptionModel>>(descriptionMapper) {
        FoodDescriptionApiMapper()
    }

}