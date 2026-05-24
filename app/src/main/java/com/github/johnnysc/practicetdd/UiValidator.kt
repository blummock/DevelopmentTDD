package com.github.johnnysc.practicetdd

interface UiValidator {
    fun isValid(text: String): Boolean
    fun errorMessage(): String
}