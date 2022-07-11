package com.github.fatihsokmen.coinsmarketcap.domain

import androidx.paging.PagingData
import androidx.paging.map
import com.github.fatihsokmen.coinsmarketcap.data.CryptoAssetRepository
import com.github.fatihsokmen.coinsmarketcap.presentation.dashboard.CryptoAssetDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.DecimalFormat
import java.text.NumberFormat

class ObserveCryptoAssetsUseCase(
    private val cryptoAssetRepository: CryptoAssetRepository,
    private val priceFormatter: NumberFormat = DecimalFormat("#.###")
) {

    fun execute(): Flow<PagingData<CryptoAssetDomain>> =
        cryptoAssetRepository.loadCryptoAssets().map { data ->
            data.map {
                CryptoAssetDomain(
                    id = it.id,
                    name = it.name,
                    symbol = it.symbol,
                    rank = it.rank,
                    icon = it.icon,
                    price = "$CURRENCY${priceFormatter.format(it.price)}"
                )
            }
        }

    companion object {
        const val CURRENCY = "US$"
    }
}