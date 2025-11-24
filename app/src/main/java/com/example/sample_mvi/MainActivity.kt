package com.example.sample_mvi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.sample_mvi.core.common.circuit.CircuitScreen
import com.example.sample_mvi.core.common.ui.theme.SamplemviTheme
import com.example.sample_mvi.features.example.ExamplePresenter
import com.example.sample_mvi.features.example.ExampleScreen
import com.example.sample_mvi.features.example.ExampleUiFactory
import com.slack.circuit.runtime.presenter.presenterOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            SamplemviTheme {
                Scaffold { paddingValues ->
                    CircuitScreen {
                        presenter<ExampleScreen, ExampleScreen.State> { screen ->
                            presenterOf { ExamplePresenter() }
                        }

                        ui(ExampleUiFactory())

                        content(
                            screen = ExampleScreen,
                            modifier = Modifier.padding(paddingValues)
                        )
                    }
                }
            }
        }
    }
}