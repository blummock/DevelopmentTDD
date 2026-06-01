package com.github.johnnysc.practicetdd

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

interface Repository {

    suspend fun jokes(): List<String>

    class Base(private val service: SomeService, private val dispatcher: CoroutineDispatcher) : Repository {
        override suspend fun jokes() = withContext(dispatcher) {
            (0..9).map { async { service.load() } }.awaitAll()
        }
    }
}