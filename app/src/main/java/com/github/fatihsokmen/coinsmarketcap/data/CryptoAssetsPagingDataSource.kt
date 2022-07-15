package com.github.fatihsokmen.coinsmarketcap.data

import androidx.paging.PagingSource
import androidx.paging.PagingState

class CryptoAssetsPagingDataSource(private val api: CryptoAssetApiService) :
    PagingSource<Int, CryptoAssetDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CryptoAssetDto> {
        val key = params.key ?: 0
        return try {
            val assets =
                api.getCryptoAssets(skip = key, limit = PAGING_SIZE, currency = "USD").coins

            LoadResult.Page(
                data = assets,
                prevKey = null,
                nextKey = key + PAGING_SIZE
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CryptoAssetDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(PAGING_SIZE)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(PAGING_SIZE)
        }
    }

    companion object {
        const val PAGING_SIZE: Int = 50
    }
}

class CryptoAssetsPagingDataSourceFactory(
    private val api: CryptoAssetApiService
) {

    operator fun invoke(): (() -> PagingSource<Int, CryptoAssetDto>) =
        { CryptoAssetsPagingDataSource(api) }
}