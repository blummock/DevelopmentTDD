package com.github.johnnysc.practicetdd

interface Contains {
    fun contains(collection: List<String>, item: String): Boolean

    class Base(private val block: For) : Contains {
        override fun contains(collection: List<String>, item: String): Boolean {
            var found = false
            block.repeat(max = collection.size) {
                if (collection[it] == item) {
                    found = true
                    true
                } else {
                    false
                }
            }
            return found
        }
    }
}
