package com.factorial.common.ext

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ScopeExt {

    companion object {

        fun CoroutineScope.launchSafeIO(
            onError: (Throwable) -> Unit = {},
            block: suspend CoroutineScope.() -> Unit,
        ): Job = launchSafe(onError, block, Dispatchers.IO)

        fun CoroutineScope.launchSafeDefault(
            onError: (Throwable) -> Unit = {},
            block: suspend CoroutineScope.() -> Unit,
        ): Job = launchSafe(onError, block, Dispatchers.Default)

        fun CoroutineScope.launchSafeMain(
            onError: (Throwable) -> Unit = {},
            block: suspend CoroutineScope.() -> Unit,
        ): Job = launchSafe(onError, block, Dispatchers.Main)

        private fun CoroutineScope.launchSafe(
            onError: (Throwable) -> Unit = {},
            block: suspend CoroutineScope.() -> Unit,
            dispatcher: CoroutineDispatcher = Dispatchers.Main,
        ): Job {
            val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                onError(throwable)
            }
            return launch(exceptionHandler + dispatcher) { block() }
        }

    }

}