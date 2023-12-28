package com.factorial.data.mapper

import com.factorial.common.const.Const


// Interface for mapping data from one object to another
// Abstract class for mapping data from one object to another with list impl
// Also contains Image Url builder

interface Mapper<I, O> {
    fun map(input: I): O
}

interface ListMapper<I, O> : Mapper<I, O> {
    fun map(input: List<I>): List<O>
}

abstract class BaseMapper<I, O> : ListMapper<I, O> {
    abstract override fun map(input: I): O

    override fun map(input: List<I>): List<O> = input.map { map(it) }

    protected fun buildImageUrl(image: String?): String? = image?.let { Const.IMAGE_URL + it }

}