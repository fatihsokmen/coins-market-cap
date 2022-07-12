package com.github.fatihsokmen.coinsmarketcap.presentation.dashboard

import androidx.paging.PagingData
import app.cash.turbine.test
import com.github.fatihsokmen.coinsmarketcap.MainCoroutineRule
import com.github.fatihsokmen.coinsmarketcap.domain.ObserveCryptoAssetsUseCase
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class DashboardViewModelTest {

    @get:Rule
    val rule = MainCoroutineRule()

    private lateinit var observeCryptoAssetsUseCase: ObserveCryptoAssetsUseCase

    @Before
    fun setup() {
        observeCryptoAssetsUseCase = mockk(relaxed = true) {
            every { execute() } returns flowOf(
                PagingData.from(listOf(mockk(), mockk(), mockk()))
            )
        }

    }

    @Test
    fun `GIVEN view model WHEN items observed THEN use case should load data`() = runTest {
        val sut = DashboardViewModel(observeCryptoAssetsUseCase)

        sut.items.test {
            awaitItem().shouldBeInstanceOf<PagingData<CryptoAssetDomain>>()
        }
    }

}