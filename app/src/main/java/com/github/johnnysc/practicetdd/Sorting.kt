package com.github.johnnysc.practicetdd

interface Sorting {

    fun sort(list: List<Int>): List<Int>

    class Base(
        private val forOut: For,
        private val forIn: For
    ) : Sorting {

        override fun sort(list: List<Int>): List<Int> {
            val mutable = list.toMutableList()

            forOut.repeat(max = mutable.size) { outIndex ->

                var swapped = false

                forIn.repeat(max = mutable.size - 1 - outIndex) { inIndex ->

                    if (mutable[inIndex] > mutable[inIndex + 1]) {
                        val temp = mutable[inIndex]
                        mutable[inIndex] = mutable[inIndex + 1]
                        mutable[inIndex + 1] = temp

                        swapped = true
                    }

                    false
                }

                !swapped
            }

            return mutable
        }
    }
}