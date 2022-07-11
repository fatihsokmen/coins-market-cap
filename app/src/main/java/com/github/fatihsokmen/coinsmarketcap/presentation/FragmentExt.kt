package com.github.fatihsokmen.coinsmarketcap.presentation

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.github.fatihsokmen.coinsmarketcap.presentation.routing.Route
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

inline fun Fragment.bindOnStart(
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline block: suspend CoroutineScope.() -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(
            minActiveState
        ) { block() }
    }
}

fun <EVENT> Fragment.observeViewModel(
    viewModel: ViewModel<EVENT>,
    handleEvent: (event: EVENT) -> Unit,
    handleRoute: (route: Route) -> Unit
) {
    bindOnStart {
        viewModel.event.collectLatest {
            handleEvent(it)
        }
    }

    bindOnStart {
        viewModel.route.collectLatest {
            handleRoute(it)
        }
    }
}
