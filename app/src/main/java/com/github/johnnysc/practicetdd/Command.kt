package com.github.johnnysc.practicetdd

interface Command<T, U> {

    fun canHandle(message: String): Boolean

    suspend fun handle(useCase: U): MessageUI

    abstract class Abstract<T : U, U>(
        private val parser: Parser<U>
    ) : Command<T, U> {
        private var handleUseCase: IsEmptyHandleUseCase<U> = IsEmptyHandleUseCase.Empty()

        override fun canHandle(message: String): Boolean {
            handleUseCase = parser.map(message)
            return message.isNotBlank()
        }

        override suspend fun handle(useCase: U): MessageUI {
            return handleUseCase.handle(useCase)
        }
    }
}
