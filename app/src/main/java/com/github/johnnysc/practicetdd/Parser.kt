package com.github.johnnysc.practicetdd

interface Parser {

    fun parse(raw: String): List<Any>

    class Base(private val delimiter: String) : Parser {

        init {
            if (delimiter.isEmpty()) throw IllegalStateException()
        }

        override fun parse(raw: String) = raw.split(delimiter).mapNotNull { parseValue(it) }

        private fun parseValue(value: String): Any? {

            //Empty
            if (value.isEmpty()) return null
            //Char
            if (value.length == 1 && value[0] - '0' !in 0..9) return value[0]
            // Boolean
            if (value == "true") return true
            if (value == "false") return false

            // Numeric
            val numeric = value.toByteOrNull()
                ?: value.toShortOrNull()
                ?: value.toIntOrNull()
                ?: value.toLongOrNull()
                ?: value.takeIf { it.substringBefore(".").length < FLOAT_FRONTIER }?.toFloatOrNull()
                    ?.takeIf { !it.isInfinite() }
                ?: value.toDoubleOrNull()?.takeIf { !it.isInfinite() }
            if (numeric != null) return numeric
            // String
            return value
        }
    }

    companion object {
        private const val FLOAT_FRONTIER: Byte = 20
    }
}
