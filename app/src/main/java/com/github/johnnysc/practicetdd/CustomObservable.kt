package com.github.johnnysc.practicetdd

interface CustomObservable<T, N> {

    fun addObserver(observer: N)

    fun update(item: T)

    class Base<T : CustomObject, N : CustomObserver<T>> : CustomObservable<T, N> {

        private val observers = mutableListOf<N>()

        override fun addObserver(observer: N) {
            observers.add(observer)
        }

        override fun update(item: T) {
            if (item is CustomObject.Premium) {
                observers.filterIsInstance<CustomObserver.Premium<T>>().forEach { it.update(item) }
            } else {
                observers.forEach { it.update(item) }
            }
        }
    }
}