package com.factorial.testtask.ui.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.factorial.common.ext.ScopeExt.Companion.launchSafeIO
import com.factorial.common.utils.Resource
import com.factorial.common.utils.Resource.Companion.toResource
import com.factorial.domain.usecase.GetFoodDescriptionByIdUseCase
import com.factorial.testtask.model.FoodInfoModel
import com.factorial.testtask.model.FoodUIListModel.FoodUIItemModel.Companion.toUiModel
import kotlinx.coroutines.delay

class InfoViewModel(
    savedStateHandle: SavedStateHandle,
    private val getFoodDescriptionByIdUseCase: GetFoodDescriptionByIdUseCase,
) : ViewModel() {

    private val foodModel = InfoFragmentArgs.fromSavedStateHandle(savedStateHandle).item

    private val _status: MutableLiveData<Resource<FoodInfoModel>> = MutableLiveData()
    val status: LiveData<Resource<FoodInfoModel>> = _status

    init {
        getDescription()
    }

    private fun getDescription() {
        viewModelScope.launchSafeIO(::handleError) {
            _status.postValue(Resource.Loading())

            // Delay only for demonstration animation of progress bar; remove it in real project
            delay(2000)

            val foodList = getFoodDescriptionByIdUseCase(foodModel.id)
                .mapCatching { FoodInfoModel.from(foodModel.toUiModel(), it.description) }
                .toResource()

            _status.postValue(foodList)
        }
    }

    private fun handleError(throwable: Throwable) {
        _status.postValue(Resource.Error(throwable))
    }

}