package com.example.sample_mvi.core.common.circuit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.screen.PopResult
import com.slack.circuit.runtime.screen.Screen

class CircuitNav3Navigator(
    private val backStack: MutableList<Screen> = mutableListOf()
) : Navigator {

    override fun goTo(screen: Screen): Boolean {
        return backStack.add(screen)
    }

    override fun peek(): Screen? {
        return backStack.lastOrNull()
    }

    override fun peekBackStack(): List<Screen> {
        return backStack.toList()
    }

    override fun pop(result: PopResult?): Screen? {
        return backStack.removeLastOrNull()
    }

    override fun resetRoot(
        newRoot: Screen,
        options: Navigator.StateOptions
    ): List<Screen> {
        backStack.clear()
        backStack.add(newRoot)
        return backStack
    }
}

@Composable
fun rememberCircuitNav3Navigator(backStack: MutableList<Screen> = mutableListOf()) = remember(backStack) {
    CircuitNav3Navigator(backStack)
}


