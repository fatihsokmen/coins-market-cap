package com.github.fatihsokmen.coinsmarketcap.domain

import androidx.paging.PagingData
import app.cash.turbine.test
import com.github.fatihsokmen.coinsmarketcap.data.CryptoAssetDto
import com.github.fatihsokmen.coinsmarketcap.data.CryptoAssetRepository
import io.kotest.matchers.shouldNotBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.text.NumberFormat

@OptIn(ExperimentalCoroutinesApi::class)
internal class ObserveCryptoAssetsUseCaseTest {

    private val repository = mockk<CryptoAssetRepository>(relaxed = true)
    private val formatter = mockk<NumberFormat>()
    private val sut = ObserveCryptoAssetsUseCase(repository, formatter)

    @Test
    fun `GIVEN use case WHEN assets data requested THEN repository should load first page`() =
        runTest {
            val pagingData = mockk<PagingData<CryptoAssetDto>>(relaxed = true)
            val flow = flowOf(pagingData)
            coEvery { repository.loadCryptoAssets() } returns flow

            sut.execute().test {
                awaitItem() shouldNotBe null
                awaitComplete()
            }
        }
}