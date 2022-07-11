package com.github.fatihsokmen.coinsmarketcap.presentation.routing

import androidx.navigation.NavController
import com.github.fatihsokmen.coinsmarketcap.presentation.dashboard.DashboardRoute
import com.github.fatihsokmen.coinsmarketcap.presentation.dashboard.DashboardRouter

class AppRouter(private val controller: NavController) {

    fun navigate(route: Route) {
        when (route) {
            is DashboardRoute -> DashboardRouter(controller).navigate(route)
        }
    }
}
