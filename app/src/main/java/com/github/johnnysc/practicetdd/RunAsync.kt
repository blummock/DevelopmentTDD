package com.github.johnnysc.practicetdd

import kotlinx.coroutines.CoroutineScope

interface RunAsync {

    fun <T : Any> async(
        scope: CoroutineScope,
        background: suspend () -> T,
        ui: (T) -> Unit
    )
}