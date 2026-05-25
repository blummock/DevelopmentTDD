package com.github.johnnysc.practicetdd

interface CustomObserver<T> {

    fun update(obj: T)

    abstract class Usual<T> : CustomObserver<T>

    abstract class Premium<T> : CustomObserver<T>
}