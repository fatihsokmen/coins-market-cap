package com.github.fatihsokmen.coinsmarketcap.data

import androidx.paging.Pager
import androidx.paging.PagingConfig

class CryptoAssetRepository(
    private val apiService: CryptoAssetApiService
) {
    fun loadCryptoAssets() =
        Pager(
            config = PagingConfig(
                pageSize = CryptoAssetsPagingDataSource.PAGING_SIZE,
                enablePlaceholders = false,
                initialLoadSize = CryptoAssetsPagingDataSource.PAGING_SIZE * 2
            ),
            pagingSourceFactory = {
                CryptoAssetsPagingDataSource(apiService)
            },
            initialKey = 0
        ).flow

    suspend fun loadCryptoAssetChart(id: String) =
        apiService.getCryptoAssetChart(id)

}