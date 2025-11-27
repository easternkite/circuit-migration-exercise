package com.example.sample_mvi.features.example

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data object ExampleScreen : Screen {
    data class State(
        val isLoading: Boolean = false,
        val data: String = "",
        val errorMessage: String = "",
        val eventSink: (Event) -> Unit = {}
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent {
        data class RequestData(val data: String) : Event
        data class Error(val message: String) : Event
        data class ShowToast(val message: String) : Event
        object NavigateToDetail : Event
    }
}



