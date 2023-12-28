package com.factorial.testtask.di

import com.factorial.testtask.ui.info.InfoViewModel
import com.factorial.testtask.ui.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiDi = module {

    viewModel {
        ListViewModel(
            getFoodListUseCase = get(),
        )
    }

    viewModel {
        InfoViewModel(
            savedStateHandle = get(),
            getFoodDescriptionByIdUseCase = get(),
        )
    }

}