package com.example.sample_mvi.features.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    state: DetailScreen.State,
) {
    Box(modifier) {
        Column {
            Text(text = state.content)
        }
    }
}

class DetailUiFactory : Ui.Factory {
    override fun create(
        screen: Screen,
        context: CircuitContext
    ): Ui<*>? {
        return when(screen) {
            is DetailScreen -> {
                ui<DetailScreen.State> { state, modifier ->
                    DetailScreen(modifier.fillMaxSize(), state)
                }
            }
            else -> null
        }
    }
}
