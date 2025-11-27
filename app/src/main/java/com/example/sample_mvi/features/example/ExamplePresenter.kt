package com.example.sample_mvi.features.example

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.example.sample_mvi.features.detail.DetailScreen
import com.slack.circuit.retained.rememberRetained
import com.slack.circuit.runtime.Navigator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ExamplePresenter(
    navigator: Navigator
): ExampleScreen.State {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val isLoading = rememberRetained { mutableStateOf(false) }
    val data = rememberRetained { mutableStateOf("") }
    val errorMessage = rememberRetained { mutableStateOf("") }

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
            is ExampleScreen.Event.NavigateToDetail -> {
                navigator.goTo(DetailScreen)
            }
        }
    }
}