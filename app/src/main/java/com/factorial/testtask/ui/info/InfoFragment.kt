package com.factorial.testtask.ui.info

import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionManager
import coil.load
import com.factorial.common.base.BaseFragment
import com.factorial.common.utils.Resource
import com.factorial.testtask.databinding.FragmentInfoBinding
import com.factorial.testtask.model.FoodInfoModel
import com.google.android.material.transition.MaterialFade
import org.koin.androidx.viewmodel.ext.android.viewModel

class InfoFragment : BaseFragment<FragmentInfoBinding>(FragmentInfoBinding::inflate) {

    private val viewModel: InfoViewModel by viewModel()

    override fun initView() = with(binding) {
        ivBack.setOnClickListener { findNavController().navigateUp() }
    }

    override fun initObs() = with(viewModel) {
        status.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    showError(it)
                    showLoadingOrContent(false)
                }

                is Resource.Loading -> showLoadingOrContent(true)

                is Resource.Success -> {
                    updateUI(it.data!!)
                    showLoadingOrContent(false)
                }
            }
        }
    }

    private fun updateUI(data: FoodInfoModel) = with(binding) {
        tvTitle.text = data.name
        tvDescription.text = data.description
        imageView.load(data.image)
        cardView.setCardBackgroundColor(data.foodUIItemModel.colorHex)
    }

    private fun showLoadingOrContent(isLoading: Boolean) = with(binding) {
        TransitionManager.beginDelayedTransition(root, MaterialFade())
        progressBar.isVisible = isLoading
        cardView.isVisible = !isLoading
        tvTitle.isVisible = !isLoading
    }

}
