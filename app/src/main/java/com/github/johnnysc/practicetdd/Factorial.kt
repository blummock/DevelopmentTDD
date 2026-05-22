package com.github.johnnysc.practicetdd


interface Factorial<T : Number> {
    fun value(number: T): T

    class Int : Factorial<kotlin.Int> {
        override fun value(number: kotlin.Int): kotlin.Int {
            if (number < 0) throw IllegalArgumentException()
            if (number == 0) return 1
            if (number > 12) throw IllegalArgumentException()
            var result = 1
            for (i in 1..number) {
                result *= i
            }
            return result
        }
    }

    class Double : Factorial<kotlin.Double> {
        override fun value(number: kotlin.Double): kotlin.Double {
            if (number < 0) throw IllegalArgumentException()
            if (number == 0.0) return 1.0
            if (number > 170.0) throw IllegalArgumentException()
            var result = 1.0
            for (i in 1..number.toInt()) {
                result *= i.toDouble()
            }
            return result
        }
    }

    class BigInteger : Factorial<java.math.BigInteger> {
        override fun value(number: java.math.BigInteger): java.math.BigInteger {
            if (number < java.math.BigInteger.ZERO) throw IllegalStateException()
            if (number == java.math.BigInteger.ZERO) return java.math.BigInteger.ONE
            if (number > java.math.BigInteger.valueOf(11000)) throw IllegalStateException()
            var result = java.math.BigInteger.ONE
            var i = java.math.BigInteger.ONE
            while (i <= number) {
                result = result.multiply(i)
                i = i.add(java.math.BigInteger.ONE)
            }
            return result
        }
    }

    class Factory(
        private val int: Factorial<kotlin.Int>,
        private val double: Factorial<kotlin.Double>,
        private val bigInteger: Factorial<java.math.BigInteger>
    ) {
        fun value(number: Number): Number {
            if (number.toDouble() < 0) throw IllegalArgumentException()
            return when (number) {
                0 -> 1
                in 1..12 -> int.value(number.toInt())
                in 13..170 -> double.value(number.toDouble())
                in 171..11000 -> bigInteger.value(java.math.BigInteger.valueOf(number.toLong()))
                else -> throw IllegalStateException()
            }
        }
    }
}
