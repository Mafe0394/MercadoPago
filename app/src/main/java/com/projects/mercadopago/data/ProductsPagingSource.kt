package com.projects.mercadopago.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.projects.mercadopago.data.domain.Product
import com.projects.mercadopago.data.domain.asDomainModel

private const val MERCADOPAGO_STARTING_PAGE_OFFSET = 1
const val NETWORK_PAGE_SIZE = 50
private const val INITIAL_LOAD_SIZE = 0

class ProductsPagingSource(
    private val mercadoPagoNetwork:ProductsDataSource,
    private val query:String
):PagingSource<Int,Product>() {
    // The refresh key is used for subsequent refresh calls to PagingSource.load after the initial load
    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        // Start refresh at position 1 if undefined.
        val position = params.key ?: INITIAL_LOAD_SIZE
        val offset = if (params.key != null) ((position - 1) * NETWORK_PAGE_SIZE) + 1 else INITIAL_LOAD_SIZE
        return try {
            val response = mercadoPagoNetwork.getQueryWithOffset(query, offset)
            val results = response.asDomainModel()
            val nextKey = if (results.isEmpty()) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = results,
                prevKey = null, // Only paging forward.
                // assume that if a full page is not loaded, that means the end of the data
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}