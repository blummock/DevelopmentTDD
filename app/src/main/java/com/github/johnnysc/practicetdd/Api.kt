package com.github.johnnysc.practicetdd

interface Api {

    fun fetch(body: RequestBody, callback: Callback)

    interface RequestBody {
        fun isEmpty(): Boolean

        class Base(private val text: String) : RequestBody {
            override fun isEmpty(): Boolean = text.isEmpty()
        }
    }

    interface Callback {
        fun provideSuccess(data: Result.Success)
        fun provideError(e: Result.Error)
    }

    sealed class Result {
        data class Success(val data: String) : Result()
        data class Error(val e: IllegalStateException) : Result()
    }
}
