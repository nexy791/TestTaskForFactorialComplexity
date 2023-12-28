package com.factorial.testtask.ui.list

import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionManager
import com.factorial.common.base.BaseFragment
import com.factorial.common.utils.Resource
import com.factorial.testtask.databinding.FragmentListBinding
import com.factorial.testtask.model.FoodUIListModel
import com.factorial.testtask.ui.adapter.FoodAdapter
import com.google.android.material.transition.MaterialSharedAxis
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::inflate) {

    private var mAdapter: FoodAdapter? = null
    private val viewModel: ListViewModel by viewModel()

    override fun initView() {
        initToolbar()
        initList()
    }

    private fun initToolbar() = with(binding) {
        ivRefresh.setOnClickListener { viewModel.retry() }
    }

    private fun initList() = with(binding) {
        mAdapter = FoodAdapter {
            // its better to pass only id and get data from db or api
            // but for this test task i will pass whole model
            // and get data from bundle in InfoFragment
            // also i don't have db in this project so i can't use it
            findNavController().navigate(ListFragmentDirections.actionListFragmentToInfoFragment(it))
        }
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
    }

    override fun initObs() = with(viewModel) {
        foodStatus.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    showError(it)
                    showLoadingOrContent(false)
                }

                is Resource.Loading -> showLoadingOrContent(true)

                is Resource.Success -> updateUI(it.data!!) { showLoadingOrContent(false) }
            }
        }
    }

    private fun updateUI(data: FoodUIListModel, callback: () -> Unit) {
        binding.tvTitle.text = data.title
        mAdapter?.submitList(data.items) { callback() }
    }

    private fun showLoadingOrContent(isLoading: Boolean) = with(binding) {
        TransitionManager.beginDelayedTransition(
            root,
            MaterialSharedAxis(MaterialSharedAxis.Y, isLoading).apply {
                duration = 1_000L
            }
        )
        progressBar.isVisible = isLoading
        ivRefresh.isEnabled = !isLoading
        recyclerView.isVisible = !isLoading
        tvTitle.isVisible = !isLoading
    }

    override fun clear() {
        super.clear()
        mAdapter = null
    }


}