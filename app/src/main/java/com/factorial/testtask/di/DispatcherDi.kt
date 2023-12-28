package com.factorial.testtask.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val IO = named("IO")
val Main = named("Main")
val Default = named("Default")

val dispatcherDi = module {

    single(IO) {
        Dispatchers.IO
    }

    single(Main) {
        Dispatchers.Main
    }

    single(Default) {
        Dispatchers.Default
    }

}
