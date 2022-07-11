package com.github.fatihsokmen.coinsmarketcap.presentation

import androidx.lifecycle.viewModelScope
import com.github.fatihsokmen.coinsmarketcap.presentation.routing.Route
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

open class ViewModel<EVENT> : androidx.lifecycle.ViewModel() {
    internal val container = ViewModelContainer<EVENT>()

    override fun onCleared() {
        super.onCleared()
        container.clear()
    }
}

internal suspend fun <EVENT> ViewModel<EVENT>.postEvent(event: EVENT) {
    container.postEvent(event)
}

internal fun <EVENT> ViewModel<EVENT>.navigate(route: Route) {
    viewModelScope.launch { container.navigate(route) }
}

val <EVENT> ViewModel<EVENT>.event: Flow<EVENT>
    get() = container.event

val <EVENT> ViewModel<EVENT>.route: Flow<Route>
    get() = container.route
