package com.github.johnnysc.practicetdd

interface LoginRepository {

    suspend fun login(credentials: LoginCredentials): LoginResult
}