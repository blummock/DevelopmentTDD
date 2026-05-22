package com.github.johnnysc.practicetdd

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import java.security.acl.Owner

interface Communication<T> {

    fun map(source: T)

    fun observe(owner: LifecycleOwner, observer: Observer<T>)
}