package com.github.fatihsokmen.coinsmarketcap.data

import io.kotest.inspectors.runTest
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class CryptoAssetsRemoteDataSourceTest {

    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN remote data source WHEN char data is requested THEN api should be called`() {
        runTest(dispatcher) {
            scope.launch {
                val api = mockk<CryptoAssetApiService>(relaxed = true)
                val response = CryptoAssetChartResponse(emptyList())
                coEvery { api.getCryptoAssetChart("bitcoin") } returns response

                CryptoAssetsRemoteDataSource(api).getCryptoAssetChart("bitcoin") shouldBe response
            }
        }
    }
}