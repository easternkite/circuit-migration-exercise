package com.example.sample_mvi.features.detail

import androidx.compose.runtime.Composable

@Composable
fun DetailPresenter(): DetailScreen.State {
    return DetailScreen.State(
        content = "Hello, world"
    )
}
