package com.github.johnnysc.practicetdd

interface SimpleRepository {

    suspend fun data(): LoadResult
}