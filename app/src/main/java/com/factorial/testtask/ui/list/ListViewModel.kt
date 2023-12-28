package com.factorial.testtask.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.factorial.common.ext.ScopeExt.Companion.launchSafeIO
import com.factorial.common.utils.Resource
import com.factorial.common.utils.Resource.Companion.toResource
import com.factorial.domain.usecase.GetFoodListUseCase
import com.factorial.testtask.model.FoodUIListModel
import com.factorial.testtask.model.FoodUIListModel.Companion.toUiModel
import kotlinx.coroutines.delay

class ListViewModel(
    private val getFoodListUseCase: GetFoodListUseCase,
) : ViewModel() {

    private val _foodStatus: MutableLiveData<Resource<FoodUIListModel>> = MutableLiveData()
    val foodStatus: MutableLiveData<Resource<FoodUIListModel>> = _foodStatus

    init {
        getFoodList()
    }

    fun retry() {
        getFoodList()
    }

    private fun getFoodList() {
        viewModelScope.launchSafeIO(::handleError) {
            _foodStatus.postValue(Resource.Loading())

            // Delay only for demonstration animation of progress bar; remove it in real project
            delay(2000)

            val foodList = getFoodListUseCase()
                .mapCatching { it.toUiModel() }
                .toResource()

            _foodStatus.postValue(foodList)
        }
    }

    private fun handleError(throwable: Throwable) {
        _foodStatus.postValue(Resource.Error(throwable))
    }


}