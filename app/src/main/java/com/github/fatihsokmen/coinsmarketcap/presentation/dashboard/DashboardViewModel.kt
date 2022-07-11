package com.github.fatihsokmen.coinsmarketcap.presentation.dashboard

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.github.fatihsokmen.coinsmarketcap.domain.ObserveCryptoAssetsUseCase
import com.github.fatihsokmen.coinsmarketcap.presentation.ViewModel
import com.github.fatihsokmen.coinsmarketcap.presentation.navigate
import com.github.fatihsokmen.coinsmarketcap.presentation.postEvent
import com.github.fatihsokmen.coinsmarketcap.presentation.routing.Route
import kotlinx.coroutines.launch

class DashboardViewModel(
    observeDashboardItemsUseCase: ObserveCryptoAssetsUseCase,
) : ViewModel<DashboardEvent>() {

    val items = observeDashboardItemsUseCase.execute()
        .cachedIn(viewModelScope)

    fun refreshData() = viewModelScope.launch {
        postEvent(DashboardEvent.Refresh)
    }

    fun showDetails(asset: CryptoAssetDomain) {
        navigate(DashboardRoute.NavigateToDetails(asset.toParcelable()))
    }
}

sealed class DashboardEvent {
    object Refresh : DashboardEvent()
}

sealed class DashboardRoute : Route {
    data class NavigateToDetails(val parcel: CryptoAssetParcelable) : DashboardRoute()

}
