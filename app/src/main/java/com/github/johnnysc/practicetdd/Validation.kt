package com.github.johnnysc.practicetdd

interface Validation {
    fun isValid(text: String): Result

    sealed class Result {
        object Valid : Result()
        data class MinLengthInsufficient(val minLength: Int) : Result()
        data class UpperCaseLettersCountInsufficient(val upperCaseLettersCount: Int) : Result()
        data class LowerCaseLettersCountInsufficient(val lowerCaseLettersCount: Int) : Result()
        data class NumbersCountInsufficient(val numbersCount: Int) : Result()
        data class SpecialSignsInsufficient(val specialSignsCount: Int) : Result()
    }

    class Password(
        private val minLength: Int = 1,
        private val upperCaseLettersCount: Int = 0,
        private val lowerCaseLettersCount: Int = 0,
        private val numbersCount: Int = 0,
        private val specialSignsCount: Int = 0
    ) : Validation {

        init {
            if (minLength < 1 || upperCaseLettersCount < 0 || lowerCaseLettersCount < 0 || numbersCount < 0 || specialSignsCount < 0) {
                throw IllegalStateException("Validation rules cannot be negative or zero for minLength")
            }
        }

        override fun isValid(text: String): Result {
            if (text.length < minLength) {
                return Result.MinLengthInsufficient(minLength)
            }

            val actualUpperCaseLettersCount = text.count { it.isUpperCase() }
            if (actualUpperCaseLettersCount < upperCaseLettersCount) {
                return Result.UpperCaseLettersCountInsufficient(upperCaseLettersCount)
            }

            val actualLowerCaseLettersCount = text.count { it.isLowerCase() }
            if (actualLowerCaseLettersCount < lowerCaseLettersCount) {
                return Result.LowerCaseLettersCountInsufficient(lowerCaseLettersCount)
            }

            val actualNumbersCount = text.count { it.isDigit() }
            if (actualNumbersCount < numbersCount) {
                return Result.NumbersCountInsufficient(numbersCount)
            }

            val actualSpecialSignsCount = text.count { !it.isLetterOrDigit()  }
            if (actualSpecialSignsCount < specialSignsCount) {
                return Result.SpecialSignsInsufficient(specialSignsCount)
            }

            return Result.Valid
        }
    }
}
