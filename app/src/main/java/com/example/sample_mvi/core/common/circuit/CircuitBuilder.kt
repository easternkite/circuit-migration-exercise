package com.example.sample_mvi.core.common.circuit

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.CircuitContent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui

open class CircuitScope {
    val circuit = Circuit.Builder()
    private lateinit var content: @Composable () -> Unit

    inline fun <reified S : Screen, UiState : CircuitUiState> presenter(
        crossinline factory: (screen: S, navigator: Navigator) -> Presenter<UiState>
    ) = apply {
        circuit.addPresenterFactory { screen, navigator, _ ->
            if (screen is S) {
                factory(screen, navigator)
            } else {
                null
            }

        }
    }

    fun ui(factory: Ui.Factory) {
        circuit.addUiFactory(factory)
    }

    fun content(screen: Screen, modifier: Modifier = Modifier, navigator: Navigator) {
        content = {
            CircuitContent(
                screen = screen,
                modifier = modifier,
                navigator = navigator
            )
        }
    }

    @Composable
    fun Compose() {
        CircuitCompositionLocals(circuit.build()) {
            content()
        }
    }
}

@Composable
fun CircuitScreen(block: CircuitScope.() -> Unit) {
    CircuitScope()
        .apply(block)
        .Compose()
}