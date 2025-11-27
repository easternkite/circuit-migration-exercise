package com.example.sample_mvi.features.example

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui

@Composable
fun ExampleScreen(
    modifier: Modifier = Modifier,
    state: ExampleScreen.State,
) {
    Box(modifier) {
        Column {
            Button(onClick = {
                state.eventSink(ExampleScreen.Event.RequestData("https://www.naver.com"))
            }) {
                Text("Fetch Data")
            }
            Text(text = state.data)

            Button(onClick = {
                state.eventSink(ExampleScreen.Event.NavigateToDetail)
            }) {
                Text("Go To Detail")
            }
        }

        if(state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

class ExampleUiFactory : Ui.Factory {
    override fun create(
        screen: Screen,
        context: CircuitContext
    ): Ui<*>? {
        return when(screen) {
            is ExampleScreen -> {
                ui<ExampleScreen.State> { state, modifier ->
                    ExampleScreen(modifier.fillMaxSize(), state)
                }
            }
            else -> null
        }
    }
}
