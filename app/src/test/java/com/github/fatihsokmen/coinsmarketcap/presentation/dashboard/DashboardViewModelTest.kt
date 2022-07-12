package com.github.fatihsokmen.coinsmarketcap.presentation.dashboard

import androidx.paging.PagingData
import app.cash.turbine.test
import com.github.fatihsokmen.coinsmarketcap.MainCoroutineRule
import com.github.fatihsokmen.coinsmarketcap.domain.ObserveCryptoAssetsUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class DashboardViewModelTest {

    @get:Rule
    val rule = MainCoroutineRule()

    private lateinit var observeCryptoAssetsUseCase: ObserveCryptoAssetsUseCase
    private lateinit var sut: DashboardViewModel

    @Before
    fun setup() {
        observeCryptoAssetsUseCase = mockk<ObserveCryptoAssetsUseCase>(relaxed = true)
        sut = DashboardViewModel(observeCryptoAssetsUseCase)
    }

    @Test
    fun `GIVEN view model WHEN items observed THEN use case should load data`() = runTest {
//        val sharedFlow = MutableSharedFlow<PagingData<CryptoAssetDomain>>()
//        every { observeCryptoAssetsUseCase.execute() } returns sharedFlow
//
//        sharedFlow.tryEmit(PagingData.empty())
//        sut.items.test {
//            awaitItem() shouldNotBe null
//        }
    }

}