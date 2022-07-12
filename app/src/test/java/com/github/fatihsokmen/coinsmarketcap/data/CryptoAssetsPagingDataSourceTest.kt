package com.github.fatihsokmen.coinsmarketcap.data

import androidx.paging.PagingSource
import io.kotest.matchers.collections.shouldContainInOrder
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
internal class CryptoAssetsPagingDataSourceTest {

    private val api = mockk<CryptoAssetApiService>(relaxed = true)
    private val sut = CryptoAssetsPagingDataSource(api)

    private val assets = listOf(
        CryptoAssetDto("bitcoin", "Bitcoin", "BTC", "1", "icon-btc", 20.000, 1.0),
        CryptoAssetDto("ethereum", "Ethereum", "ETH", "2", "icon-eth", 1.000, 0.54)
    )

    @Test
    fun `GIVEN paging data source WHEN load is requested THEN should load data from api`() =
        runTest {
            coEvery { api.getCryptoAssets(0, 50, "USD") } returns CryptoAssetsResponse(assets)

            val actual = sut.load(
                PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 10,
                    placeholdersEnabled = false
                )
            )

            actual.shouldBeInstanceOf<PagingSource.LoadResult.Page<Int, CryptoAssetDto>>()
            actual.data shouldContainInOrder assets
        }

    @Test
    fun `GIVEN2 paging data source WHEN load is requested THEN should load data from api`() =
        runTest {
            val exception = IOException("connection error")
            coEvery { api.getCryptoAssets(0, 50, "USD") } throws exception

            val actual = sut.load(
                PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 10,
                    placeholdersEnabled = false
                )
            )

            actual.shouldBeInstanceOf<PagingSource.LoadResult.Error<Int, CryptoAssetDto>>()
            actual.throwable shouldBe exception
        }

}