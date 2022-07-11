package com.github.fatihsokmen.coinsmarketcap.data

data class CryptoAssetChartResponse(
    val chart: List<ChartEntryDto>
)

data class ChartEntryDto(
    val time: Long,
    val price: Float
)