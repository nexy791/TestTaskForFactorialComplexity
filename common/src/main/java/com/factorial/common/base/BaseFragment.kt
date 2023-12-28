package com.factorial.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.factorial.common.alias.Inflate
import com.factorial.common.ext.IntentExt.Companion.toInternetSettings
import com.factorial.common.utils.Resource
import com.google.android.material.dialog.MaterialAlertDialogBuilder

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>,
) : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObs()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        clear()
    }


    abstract fun initView()

    protected open fun initObs() {}

    @CallSuper
    protected open fun clear() {
        _binding = null
    }

    protected fun showErrorDialog(error: Throwable) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Error")
            .setMessage(error.message.orEmpty().ifEmpty { "Unknown error" })
            .setNegativeButton("Cancel") { _, _ -> }
            .setCancelable(false)
            .show()
    }

    protected fun showNoInternetDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("No internet")
            .setMessage("Check your internet connection")
            .setPositiveButton("Turn on") { _, _ -> toInternetSettings() }
            .setNegativeButton("Cancel") { _, _ -> }
            .setCancelable(false)
            .show()
    }

    protected fun showError(status: Resource.Error<*>) {
        if (status.isInternetError()) {
            showNoInternetDialog()
        } else {
            showErrorDialog(status.error ?: Throwable("Unknown error"))
        }
    }

}