package com.github.johnnysc.practicetdd

interface Numbers {
    fun isSumLong(): Boolean
    fun sumInt(): Int
    fun sumLong(): Long
    fun difference(): Int
    fun divide(): Double

    class Base(
        private val number1: Int,
        private val number2: Int
    ) : Numbers {

        private var isSumLongCalculated = false
        private var sumIsLong = false

        override fun isSumLong(): Boolean {
            isSumLongCalculated = true
            val sum: Long = number1.toLong() + number2.toLong()
            sumIsLong = sum > Int.MAX_VALUE || sum < Int.MIN_VALUE
            return sumIsLong
        }

        override fun sumInt(): Int {
            if (!isSumLongCalculated) {
                throw IllegalAccessException("isSumLong() must be called before sumInt()")
            }
            if (sumIsLong) {
                throw IllegalStateException("Sum is too large for Int, call sumLong() instead")
            }
            return number1 + number2
        }

        override fun sumLong(): Long {
            if (!isSumLongCalculated) throw IllegalAccessException()
            if (!sumIsLong) throw IllegalStateException()
            return number1.toLong() + number2.toLong()
        }

        override fun difference(): Int = number1 - number2

        override fun divide(): Double {
            if (number2 == 0) throw IllegalArgumentException()
            return number1.toDouble() / number2.toDouble()
        }
    }
}
