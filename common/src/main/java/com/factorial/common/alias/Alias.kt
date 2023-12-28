package com.factorial.common.alias

import android.view.LayoutInflater
import android.view.ViewGroup

// use this alias for viewBinding in fragments
typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T
