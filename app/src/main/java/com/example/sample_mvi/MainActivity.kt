package com.example.sample_mvi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.sample_mvi.core.common.ui.theme.SamplemviTheme
import com.example.sample_mvi.features.example.ExamplePresenter
import com.example.sample_mvi.features.example.ExampleScreen
import com.example.sample_mvi.features.example.ExampleUiFactory
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.CircuitContent
import com.slack.circuit.runtime.presenter.presenterOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val circuit = Circuit.Builder()
            .addPresenter<ExampleScreen, ExampleScreen.State> { _, _, _ ->
                presenterOf { ExamplePresenter() }
            }
            .addUiFactory(ExampleUiFactory())
            .build()

        enableEdgeToEdge()
        setContent {
            SamplemviTheme {
                CircuitCompositionLocals(circuit) {
                    Scaffold { paddingValues ->
                        CircuitContent(ExampleScreen, modifier = Modifier.padding(paddingValues))
                    }
                }
            }
        }
    }
}