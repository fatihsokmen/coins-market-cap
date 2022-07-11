package com.github.fatihsokmen.coinsmarketcap.presentation.chart

import com.github.fatihsokmen.coinsmarketcap.domain.LoadChartDataUseCase
import com.github.fatihsokmen.coinsmarketcap.presentation.ViewModel
import com.github.fatihsokmen.coinsmarketcap.presentation.dashboard.CryptoAssetParcelable
import kotlinx.coroutines.flow.*

class ChartViewModel(
    private val chartUseCase: LoadChartDataUseCase,
    val parcel: CryptoAssetParcelable
) : ViewModel<Unit>() {

    private val _showProgress = MutableStateFlow(true)
    val showProgress = _showProgress.asStateFlow()

    val chartData = flow {
        emit(chartUseCase.execute(parcel.id).getOrNull() ?: emptyList())
    }.onStart {
        _showProgress.update { true }
    }.onCompletion {
        _showProgress.update { false }
    }
}