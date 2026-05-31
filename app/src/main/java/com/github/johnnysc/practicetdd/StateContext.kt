package com.github.johnnysc.practicetdd

interface StateContext {

    interface Actions {
        fun log(logging: Logging)
        fun next()
    }

    interface Update {
        fun updateState(state: State)
    }


    class Base(private var state: State) : Actions, Update {

        override fun log(logging: Logging) {
            logging.log(state.toString())
        }

        override fun next() {
            state.next(this)
        }

        override fun updateState(state: State) {
            this.state = state
        }
    }
}