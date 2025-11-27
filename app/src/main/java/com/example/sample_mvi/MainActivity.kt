package com.example.sample_mvi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.sample_mvi.core.common.circuit.CircuitScreen
import com.example.sample_mvi.core.common.circuit.rememberCircuitNav3Navigator
import com.example.sample_mvi.core.common.ui.theme.SamplemviTheme
import com.example.sample_mvi.features.detail.DetailPresenter
import com.example.sample_mvi.features.detail.DetailScreen
import com.example.sample_mvi.features.detail.DetailUiFactory
import com.example.sample_mvi.features.example.ExamplePresenter
import com.example.sample_mvi.features.example.ExampleScreen
import com.example.sample_mvi.features.example.ExampleUiFactory
import com.slack.circuit.runtime.presenter.presenterOf
import com.slack.circuit.runtime.screen.Screen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {

            SamplemviTheme {
                val backStack = rememberSaveable { mutableStateListOf<Screen>(ExampleScreen) }
                val navigator = rememberCircuitNav3Navigator(backStack)

                Scaffold { paddingValues ->
                    NavDisplay(
                        backStack = backStack,
                        onBack = { backStack.removeLastOrNull() },
                        entryProvider = entryProvider {
                            entry<ExampleScreen> {
                                CircuitScreen {
                                    presenter<ExampleScreen, ExampleScreen.State> { screen, navigator ->
                                        presenterOf { ExamplePresenter(navigator) }
                                    }

                                    ui(ExampleUiFactory())

                                    content(
                                        screen = ExampleScreen,
                                        modifier = Modifier.padding(paddingValues),
                                        navigator = navigator
                                    )
                                }
                            }

                            entry<DetailScreen> {
                                CircuitScreen {
                                    presenter<DetailScreen, DetailScreen.State> { screen, navigator ->
                                        presenterOf { DetailPresenter() }
                                    }

                                    ui(DetailUiFactory())

                                    content(
                                        screen = DetailScreen,
                                        modifier = Modifier.padding(paddingValues),
                                        navigator = navigator
                                    )
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}