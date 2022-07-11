package com.github.fatihsokmen.coinsmarketcap.presentation

import com.github.fatihsokmen.coinsmarketcap.presentation.routing.Route
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class ViewModelContainer<EVENT> {

    /**
     * SharedFlow can also be used however we need 1-to-1 communication.
     */
    private val _event = Channel<EVENT>(Channel.BUFFERED)
    val event: Flow<EVENT> = _event.receiveAsFlow()

    /**
     * SharedFlow can also be used however we need 1-to-1 communication.
     */
    private val _route = Channel<Route>(Channel.BUFFERED)
    val route: Flow<Route> = _route.receiveAsFlow()

    suspend fun postEvent(event: EVENT) = _event.send(event)

    suspend fun navigate(route: Route) = _route.send(route)
}
