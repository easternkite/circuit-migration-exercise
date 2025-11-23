package com.example.sample_mvi.features.example

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ExamplePresenter(): ExampleScreen.State {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val isLoading = remember { mutableStateOf(false) }
    val data = remember { mutableStateOf("") }
    val errorMessage = remember { mutableStateOf("") }

    return ExampleScreen.State(
        isLoading = isLoading.value,
        data = data.value,
        errorMessage = errorMessage.value,
    ) { event ->
        when(event) {
            is ExampleScreen.Event.RequestData -> {
                scope.launch {
                    isLoading.value = true
                    delay(3000)
                    data.value = event.data
                    isLoading.value = false
                }
            }
            is ExampleScreen.Event.Error -> errorMessage.value = event.message
            is ExampleScreen.Event.ShowToast -> { Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show() }
        }
    }
}