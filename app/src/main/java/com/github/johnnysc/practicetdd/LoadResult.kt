package com.github.johnnysc.practicetdd

import java.io.Serializable

interface LoadResult {

    fun <T : Serializable> map(mapper: Mapper<T>): T

    interface Mapper<T>  {
        fun map(data: DateAndName): T
        fun map(error: Exception): T
    }
}