package com.github.fatihsokmen.coinsmarketcap.presentation

import com.github.fatihsokmen.coinsmarketcap.data.*
import com.github.fatihsokmen.coinsmarketcap.domain.LoadChartDataUseCase
import com.github.fatihsokmen.coinsmarketcap.domain.ObserveCryptoAssetsUseCase
import com.github.fatihsokmen.coinsmarketcap.presentation.dashboard.DashboardViewModel
import com.github.fatihsokmen.coinsmarketcap.presentation.chart.ChartViewModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single { provideOkHttp() }
    single { provideRetrofit() }

    factory { get<Retrofit>().create(CryptoAssetApiService::class.java) }
    factory { CryptoAssetsPagingDataSourceFactory(get()) }
    factory { CryptoAssetsRemoteDataSource(get()) }
    factory { CryptoAssetRepository(get(), get()) }
    factory { ObserveCryptoAssetsUseCase(get()) }
    factory { LoadChartDataUseCase(get()) }

    viewModel { DashboardViewModel(get()) }
    viewModel { parcel -> ChartViewModel(get(), parcel.get()) }
}

private fun Scope.provideRetrofit(): Retrofit {
    val gson = GsonBuilder().registerTypeAdapter(
        CryptoAssetChartResponse::class.java,
        JsonChartDeserializer()
    ).create()

    return Retrofit.Builder()
        .baseUrl("https://api.coinstats.app/public/v1/")
        .client(get())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

private fun provideOkHttp() =
    OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()
