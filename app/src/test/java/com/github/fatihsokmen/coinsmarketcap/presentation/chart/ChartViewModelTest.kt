package com.github.fatihsokmen.coinsmarketcap.presentation.chart

import app.cash.turbine.test
import com.github.fatihsokmen.coinsmarketcap.domain.LoadChartDataUseCase
import com.github.fatihsokmen.coinsmarketcap.presentation.dashboard.CryptoAssetParcelable
import io.kotest.matchers.collections.shouldHaveSize
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class ChartViewModelTest {

    private val loadChartDataUseCase = mockk<LoadChartDataUseCase>()
    private val parcel = CryptoAssetParcelable(
        "bitcoin", "Bitcoin", "BTC", "1", "icon", "20000"
    )
    private val sut = ChartViewModel(loadChartDataUseCase, parcel)

    @Test
    fun `GIVEN view model WHEN data is requested THEN use case should load char data`() = runTest {
        val response = listOf<CryptoAssetChartDomain>(mockk(), mockk(), mockk())
        coEvery { loadChartDataUseCase.execute("bitcoin") } returns Result.success(response)

        sut.chartData.test {
            awaitItem() shouldHaveSize response.size
            cancelAndConsumeRemainingEvents()
        }
    }

}