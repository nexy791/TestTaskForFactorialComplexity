package com.factorial.common.utils

import androidx.recyclerview.widget.DiffUtil

class DefaultDiff<T : Any>(
    private val areContentsTheSame: (oldItem: T, newItem: T) -> Boolean = { _, _ -> false },
    private val areItemsTheSame: (oldItem: T, newItem: T) -> Boolean = { oldItem, newItem -> oldItem == newItem },
) : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        areItemsTheSame.invoke(oldItem, newItem)

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        areContentsTheSame.invoke(oldItem, newItem)
}