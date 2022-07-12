package com.github.fatihsokmen.coinsmarketcap.domain

import com.github.fatihsokmen.coinsmarketcap.data.CryptoAssetRepository
import com.github.fatihsokmen.coinsmarketcap.presentation.chart.CryptoAssetChartDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoadChartDataUseCase(
    private val cryptoAssetRepository: CryptoAssetRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun execute(id: String) = withContext(dispatcher) {
        runCatching {
            cryptoAssetRepository.loadCryptoAssetChart(id).map {
                CryptoAssetChartDomain(
                    time = it.time,
                    price = it.price
                )
            }
        }
    }
}