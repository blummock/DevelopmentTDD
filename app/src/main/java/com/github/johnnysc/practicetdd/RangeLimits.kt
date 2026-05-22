package com.github.johnnysc.practicetdd

interface RangeLimits {
    fun pair(number: Int): RangePair

    class Base(private val list: List<Int>) : RangeLimits {
        override fun pair(number: Int): RangePair {
            if (list.isEmpty()) {
                return RangePair(Int.MIN_VALUE, Int.MAX_VALUE)
            }

            val left = list.lastOrNull { it < number } ?: Int.MIN_VALUE
            val right = list.firstOrNull { it > number } ?: Int.MAX_VALUE

            return RangePair(left, right)
        }
    }
}
