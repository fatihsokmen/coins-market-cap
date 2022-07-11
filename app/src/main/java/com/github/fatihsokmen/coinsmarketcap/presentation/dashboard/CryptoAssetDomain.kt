package com.github.fatihsokmen.coinsmarketcap.presentation.dashboard

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

data class CryptoAssetDomain(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: String,
    val icon: String,
    val price: String
)

@Parcelize
@Keep
data class CryptoAssetParcelable(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: String,
    val icon: String,
    val price: String
) : Parcelable

fun CryptoAssetDomain.toParcelable() =
    CryptoAssetParcelable(
        id = id,
        name = name,
        symbol = symbol,
        rank = rank,
        icon = icon,
        price = price
    )