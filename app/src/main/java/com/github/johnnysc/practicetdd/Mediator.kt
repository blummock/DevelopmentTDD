package com.github.johnnysc.practicetdd

interface Mediator {
    fun change(choice: Choice, block: () -> Unit)

    class Base : Mediator {

        private var currentChoice: Choice? = null

        override fun change(choice: Choice, block: () -> Unit) {
            currentChoice?.rollback()
            currentChoice = choice
            if (!choice.isChosen()) {
                choice.chose()
            }
            block.invoke()
        }
    }
}
