package com.github.johnnysc.practicetdd

interface IsEmptyHandleUseCase<T> {

    suspend fun handle(useCase: T): MessageUI

    class Empty<T> : IsEmptyHandleUseCase<T> {
        override suspend fun handle(useCase: T) = MessageUI.Empty
    }
}