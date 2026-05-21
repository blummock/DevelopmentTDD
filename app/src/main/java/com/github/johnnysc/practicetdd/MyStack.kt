package com.github.johnnysc.practicetdd

interface MyStack<T> {

    fun pop(): T

    fun push(item: T)


    class LIFO<T>(private val maxCount: Int) : MyStack<T> {

        private val items: Array<T?>
        private var position = 0

        init {
            if (maxCount < 1) throw IllegalStateException()
            items = arrayOfNulls(maxCount)
        }


        override fun pop(): T {
            if (position == 0) throw IllegalStateException()
            return items[position - 1]?.also {
                items[position - 1] = null
                position--
            } ?: throw IllegalStateException()
        }

        override fun push(item: T) {
            if (position == maxCount) throw IllegalStateException("Stack overflow exception, maximum is $maxCount")
            items[position] = item
            position++
        }
    }

    class FIFO<T>(private val maxCount: Int) : MyStack<T> {

        private val items: Array<T?>
        private var left = 0
        private var right = 0

        init {
            if (maxCount < 1) throw IllegalStateException()
            items = arrayOfNulls(maxCount)
        }


        override fun pop(): T {
            if (left == maxCount) {
                left = 0
            }
            return items[left]?.also {
                items[left] = null
                left++
            } ?: throw IllegalStateException()
        }

        override fun push(item: T) {
            if (right == maxCount) {
                right = 0
            }
            if (items[right] != null) throw IllegalStateException("Stack overflow exception, maximum is $maxCount")
            items[right] = item
            right++
        }
    }
}