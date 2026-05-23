package com.github.johnnysc.practicetdd

data class LotteryTicket(
    val number: Int
) {

    fun isFake() =
        number < 0 || number.toString().length.let { it % 2 != 0 || it > 8 }


    fun isWinner() = number.toString().let {
        it.substring(0, it.length / 2).sumOf { dig -> dig.digitToInt() } ==
                it.substring(it.length / 2).sumOf { dig -> dig.digitToInt() }
    }
}
