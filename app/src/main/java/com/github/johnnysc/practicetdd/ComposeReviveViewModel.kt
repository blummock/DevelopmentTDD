package com.github.johnnysc.practicetdd

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ComposeReviveViewModel(
    private val runAsync: RunAsync,
    private val repository: SimpleRepository,
    private val mapper: LoadResult.Mapper<UiState>,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val viewModelScope = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())

    private val _state = MutableStateFlow<UiState>(savedStateHandle[STATE_KEY] ?: UiState.Loading)
    val state = _state.asStateFlow()

    init {
        addCloseable { viewModelScope.cancel() }
    }

    fun load() {
        runAsync.async(
            scope = viewModelScope,
            background = repository::data,
            ui = {
                val result = it.map(mapper)
                _state.value = result
                savedStateHandle[STATE_KEY] = result
            }
        )
    }

    fun retry() {
        _state.value = UiState.Loading
    }

    private companion object {
        const val STATE_KEY = "state_key"
    }
}