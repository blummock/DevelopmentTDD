package com.github.johnnysc.practicetdd

import java.util.LinkedList

interface CacheDataSource {

    fun add(item: SimpleData)
    fun data(): List<SimpleData>

    class Timed(
        private val now: Now,
        private val lifeTimeMillis: Long
    ) : CacheDataSource {
        private val cache = LinkedList<Pair<SimpleData, Long>>()

        override fun add(item: SimpleData) {
            cache.add(item to now.now())
        }

        override fun data(): List<SimpleData> {
            val current = now.now()
            val iterator = cache.iterator()
            return sequence {
                while (iterator.hasNext()) {
                    val (item, time) = iterator.next()
                    if (current - time > lifeTimeMillis) {
                        iterator.remove()
                    } else {
                        yield(item)
                    }
                }
            }.toList()
        }
    }
}