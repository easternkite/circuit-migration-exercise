package com.example.sample_mvi.features.detail

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data object DetailScreen : Screen {
    data class State(
        val content: String = "Hello, world"
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent {

    }
}


