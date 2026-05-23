package com.github.johnnysc.practicetdd

interface NewInt {
    fun isValid(number: Int): Boolean

    class Positive(private val next: NewInt? = null) : NewInt {
        override fun isValid(number: Int): Boolean {
            return if (number > 0) {
                next?.isValid(number) ?: true
            } else {
                false
            }
        }
    }

    class Negative(private val next: NewInt? = null) : NewInt {
        override fun isValid(number: Int): Boolean {
            return if (number < 0) {
                next?.isValid(number) ?: true
            } else {
                false
            }
        }
    }

    class Odd(private val next: NewInt? = null) : NewInt {
        override fun isValid(number: Int): Boolean {
            return if (number % 2 != 0) {
                next?.isValid(number) ?: true
            } else {
                false
            }
        }
    }

    class Less(private val limit: Int, private val next: NewInt? = null) : NewInt {
        override fun isValid(number: Int): Boolean {
            return if (number < limit) {
                next?.isValid(number) ?: true
            } else {
                false
            }
        }
    }
}
