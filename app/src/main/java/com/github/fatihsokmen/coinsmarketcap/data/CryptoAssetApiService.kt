package com.github.fatihsokmen.coinsmarketcap.data

import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoAssetApiService {

    @GET("coins")
    suspend fun getCryptoAssets(
        @Query("skip") skip: Int,
        @Query("limit") limit: Int,
        @Query("currency") currency: String
    ): CryptoAssetsResponse

    @GET("charts")
    suspend fun getCryptoAssetChart(
        @Query("coinId") coinId: String,
        @Query("period") period: String = "all"
    ): CryptoAssetChartResponse
}