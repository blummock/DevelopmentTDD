package com.github.johnnysc.practicetdd

sealed interface LoginResult {

    data object Success : LoginResult
    data object Block : LoginResult

    sealed class Error : LoginResult, RuntimeException() {
        data object IncorrectCredentials : Error()
        data object NoInternet : Error()
        data object ServerUnavailable : Error()
        data object Unknown : Error()
    }
}