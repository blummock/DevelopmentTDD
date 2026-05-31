package com.github.johnnysc.practicetdd

interface LoginInteractor {

    suspend fun login(credentials: LoginCredentials): LoginResult

    class Base(private val repository: LoginRepository) : LoginInteractor {

        private var attempts = 0

        override suspend fun login(credentials: LoginCredentials): LoginResult {
            return if (attempts > 2) LoginResult.Block else runCatching { repository.login(credentials) }
                .getOrElse {
                    if (it is LoginResult.Error.IncorrectCredentials) {
                        attempts++
                    }
                    it as? LoginResult ?: LoginResult.Error.Unknown
                }
        }
    }
}