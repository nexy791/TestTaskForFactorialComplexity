package com.factorial.testtask.di

import com.factorial.domain.usecase.GetFoodDescriptionByIdUseCase
import com.factorial.domain.usecase.GetFoodListUseCase
import org.koin.dsl.module

val domainDi = module {

    factory<GetFoodListUseCase> {
        GetFoodListUseCase.Base(
            foodRepository = get(),
        )
    }

    factory<GetFoodDescriptionByIdUseCase> {
        GetFoodDescriptionByIdUseCase.Base(
            foodRepository = get(),
        )
    }

}