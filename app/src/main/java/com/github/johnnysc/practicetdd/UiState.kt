package com.github.johnnysc.practicetdd

import java.io.Serializable

interface UiState : Serializable {

    object Loading : UiState
}
