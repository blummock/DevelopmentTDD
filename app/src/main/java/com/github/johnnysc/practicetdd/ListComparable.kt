package com.github.johnnysc.practicetdd

interface ListComparable {
    fun areItemsTheSame(other: ListComparable): Boolean
    fun areContentsTheSame(other: ListComparable): Boolean

    data class Base(
        private val id: String,
        private val name: String
    ) : ListComparable {
        override fun areItemsTheSame(other: ListComparable): Boolean {
            if (other !is Base) return false
            return id == other.id
        }

        override fun areContentsTheSame(other: ListComparable): Boolean {
            if (other !is Base) return false
            return this == other
        }
    }
}
