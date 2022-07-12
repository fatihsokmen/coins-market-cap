package com.github.fatihsokmen.coinsmarketcap.data

import androidx.paging.Pager
import androidx.paging.PagingConfig

class CryptoAssetRepository(
    private val remoteDataSource: CryptoAssetsRemoteDataSource,
    private val pagingDataSource: CryptoAssetsPagingDataSource
) {
    fun loadCryptoAssets() =
        Pager(
            config = PagingConfig(
                pageSize = CryptoAssetsPagingDataSource.PAGING_SIZE,
                enablePlaceholders = false,
                initialLoadSize = CryptoAssetsPagingDataSource.PAGING_SIZE * 2
            ),
            pagingSourceFactory = { pagingDataSource },
            initialKey = 0
        ).flow

    suspend fun loadCryptoAssetChart(id: String) =
        remoteDataSource.getCryptoAssetChart(id)

}