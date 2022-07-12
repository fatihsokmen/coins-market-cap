package com.github.fatihsokmen.coinsmarketcap.data

class CryptoAssetsRemoteDataSource(private val api: CryptoAssetApiService) {

    suspend fun getCryptoAssetChart(id: String) =
        api.getCryptoAssetChart(id).chart

}