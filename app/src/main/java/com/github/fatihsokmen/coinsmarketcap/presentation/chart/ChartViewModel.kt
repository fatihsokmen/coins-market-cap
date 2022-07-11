package com.github.fatihsokmen.coinsmarketcap.presentation.chart

import androidx.lifecycle.viewModelScope
import com.github.fatihsokmen.coinsmarketcap.domain.LoadChartDataUseCase
import com.github.fatihsokmen.coinsmarketcap.presentation.ViewModel
import com.github.fatihsokmen.coinsmarketcap.presentation.dashboard.CryptoAssetParcelable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChartViewModel(
    private val chartUseCase: LoadChartDataUseCase,
    val parcel: CryptoAssetParcelable
) : ViewModel<Unit>() {

    private val _chartData = MutableStateFlow<List<CryptoAssetChartDomain>>(emptyList())
    val chartData = _chartData.asStateFlow()

    private val _showProgress = MutableStateFlow(true)
    val showProgress = _showProgress.asStateFlow()

    init {
        loadChart(parcel.id)
    }

    private fun loadChart(id: String) = viewModelScope.launch {
        _showProgress.update { true }
        chartUseCase.execute(id)
            .onSuccess {
                _chartData.value = it
            }.onFailure {
                _chartData.value = emptyList()
            }
        _showProgress.update { false }
    }
}