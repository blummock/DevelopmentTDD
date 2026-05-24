package com.github.johnnysc.practicetdd

import kotlinx.coroutines.delay

interface DelayResponse {

    suspend fun <T> delayAfter(delayInMillis: Long, block: suspend () -> T): T

    class Base(private val now: Now) : DelayResponse {
        override suspend fun <T> delayAfter(delayInMillis: Long, block: suspend () -> T): T {
            val start = now.time()
            val result = block.invoke()
            val elapsed = now.time() - start
            val remainingDelay = delayInMillis - elapsed
            if (remainingDelay > 0) {
                delay(remainingDelay)
            }
            return result
        }
    }
}