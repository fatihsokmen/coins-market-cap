package com.github.fatihsokmen.coinsmarketcap.presentation.routing

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

/**
 * Route definition
 */
interface Route

/**
 * Router definition
 */
interface Router<ROUTE>

/**
 * Dispatches a route to [AppRouter]
 */
fun Fragment.handleRoute(route: Route) = AppRouter(findNavController()).navigate(route)
