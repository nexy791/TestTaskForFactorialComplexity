package com.factorial.testtask.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.factorial.common.utils.DefaultDiff
import com.factorial.testtask.databinding.ItemFoodBinding
import com.factorial.testtask.model.FoodUIListModel


class FoodAdapter(
    private val onFoodClick: (FoodUIListModel.FoodUIItemModel) -> Unit,
) :
    ListAdapter<FoodUIListModel.FoodUIItemModel, FoodAdapter.FoodViewHolder>(DefaultDiff({ oldItem, newItem -> oldItem.name == newItem.name })) {

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(binding)
    }

    inner class FoodViewHolder(private val binding: ItemFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FoodUIListModel.FoodUIItemModel) = with(binding) {
            root.setOnClickListener { onFoodClick(item) }

            root.setCardBackgroundColor(item.colorHex)
            tvFoodName.text = item.name
            ivFood.load(item.image)
        }

    }
}