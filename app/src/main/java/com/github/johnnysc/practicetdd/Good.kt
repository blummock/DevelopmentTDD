package com.github.johnnysc.practicetdd

interface Good {

    interface Mapper<W> {
        fun map(
            ram: Int,
            os: OS,
            displaySize: Double,
            processor: ProcessorType,
            price: Double
        ): W
    }

    fun <T> map(mapper: Mapper<T>): T
}