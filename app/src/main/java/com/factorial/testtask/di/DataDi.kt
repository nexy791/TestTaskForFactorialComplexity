package com.factorial.testtask.di

import com.factorial.data.repository.FoodRepositoryImpl
import com.factorial.data.source.FoodRemoteDataSource
import com.factorial.data.source.FoodRemoteDataSourceImpl
import com.factorial.domain.repository.FoodRepository
import org.koin.dsl.module

val dataDi = module {

    single<FoodRemoteDataSource> {
        FoodRemoteDataSourceImpl(
            apiService = get()
        )
    }

    single<FoodRepository> {
        FoodRepositoryImpl(
            dispatcher = get(IO),
            foodRemoteDataSource = get(),
            foodMapper = get(listMapper),
            foodDescriptionMapper = get(descriptionMapper),
        )
    }

}