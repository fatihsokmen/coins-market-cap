package com.github.fatihsokmen.coinsmarketcap.domain

import com.github.fatihsokmen.coinsmarketcap.data.ChartEntryDto
import com.github.fatihsokmen.coinsmarketcap.data.CryptoAssetChartResponse
import com.github.fatihsokmen.coinsmarketcap.data.CryptoAssetRepository
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.result.shouldBeFailure
import io.kotest.matchers.result.shouldBeSuccess
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class LoadChartDataUseCaseTest {

    private val repository = mockk<CryptoAssetRepository>(relaxed = true)
    private val dispatcher = StandardTestDispatcher()
    private val sut = LoadChartDataUseCase(repository, dispatcher)

    @Test
    fun `GIVEN use case WHEN chart data requested THEN repository should load data`() =
        runTest(dispatcher) {
            val chartData = listOf<ChartEntryDto>(
                mockk(relaxed = true),
                mockk(relaxed = true),
                mockk(relaxed = true)
            )

            coEvery { repository.loadCryptoAssetChart("bitcoin") } returns CryptoAssetChartResponse(
                chartData
            )

            val actual = sut.execute("bitcoin")

            actual.shouldBeSuccess()
            actual.getOrThrow().shouldHaveSize(3)
        }

    @Test
    fun `GIVEN use case WHEN chart data requested AND exception thrown THEN failure is returned`() =
        runTest(dispatcher) {
            coEvery { repository.loadCryptoAssetChart("bitcoin") } throws Exception("error")

            val actual = sut.execute("bitcoin")

            actual.shouldBeFailure()
        }
}