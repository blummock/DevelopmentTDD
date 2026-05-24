package com.github.johnnysc.practicetdd

sealed interface WeatherItem {

    data class Basic(
        val description: String,
        val temp: Int,
        val feelsLike: Int
    ) : WeatherItem

    data class Error(val exceptionType: ExceptionType) : WeatherItem
}
