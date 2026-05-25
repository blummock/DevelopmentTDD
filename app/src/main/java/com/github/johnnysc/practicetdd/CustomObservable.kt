package com.github.johnnysc.practicetdd

interface CustomObservable<T, S> {

    fun addObserver(observer: S)

    fun update(argument: T)

    fun removeObserver(observer: S)

    class Base<T, S : CustomObserver<T>>(private val maxCount: Int) : CustomObservable<T, S> {


        private val observers = mutableListOf<S>()

        override fun addObserver(observer: S) {
            observers.add(observer)
        }

        override fun update(argument: T) {
            observers.lastOrNull()?.update(argument)
        }

        override fun removeObserver(observer: S) {
            observers.remove(observer)
        }
    }
}