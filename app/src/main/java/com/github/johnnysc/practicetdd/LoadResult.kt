package com.github.johnnysc.practicetdd

sealed interface LoadResult {

    data class Success(val data: List<Int>) : LoadResult

    data class Error(val message: String) : LoadResult
}