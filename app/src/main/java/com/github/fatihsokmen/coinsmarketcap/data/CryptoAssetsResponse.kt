package com.github.fatihsokmen.coinsmarketcap.data

data class CryptoAssetsResponse(
    val coins: List<CryptoAssetDto>
)

data class CryptoAssetDto(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: String,
    val icon: String,
    val price: Double,
    val priceChange1d: Double
)