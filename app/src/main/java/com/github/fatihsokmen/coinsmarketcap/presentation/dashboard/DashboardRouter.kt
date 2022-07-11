package com.github.fatihsokmen.coinsmarketcap.presentation.dashboard

import androidx.navigation.NavController

class DashboardRouter(private val navController: NavController) {

    fun navigate(route: DashboardRoute) = when (route) {
        is DashboardRoute.NavigateToDetails -> {
            navController.navigate(DashboardFragmentDirections.actionOpenChart(route.parcel))
        }
    }
}