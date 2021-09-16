package com.projects.mercadopago.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.projects.mercadopago.data.domain.Paging
import com.projects.mercadopago.data.domain.Product
import com.projects.mercadopago.data.domain.asDomainModel
import com.projects.mercadopago.data.network.MercadoPagoApiService
import retrofit2.HttpException
import java.io.IOException

private const val MERCADOPAGO_STARTING_PAGE_OFFSET = 1

class ProductsPagingSource(
    private val service:MercadoPagoApiService,
    private val query:String
):PagingSource<Paging,Product>() {
    // The refresh key is used for subsequent refresh calls to PagingSource.load after the initial load
    override fun getRefreshKey(state: PagingState<Paging, Product>): Paging? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.apply {
                offset=offset?.plus(limit)?:offset?.minus(limit)
            }
        }
    }

    override suspend fun load(params: LoadParams<Paging>): LoadResult<Paging, Product> {
        val position = params.key?.offset ?: MERCADOPAGO_STARTING_PAGE_OFFSET
        val apiQuery = query
        return try {
            val response = service.getProductsByQueryWithOffset(apiQuery, position)
            val products = response.asDomainModel()
            val nextKey = if (products.isEmpty()) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
                params.key?.apply {
                    offset = position + (params.loadSize / limit)
                }
            }
            LoadResult.Page(data = products,
                prevKey =if(position== MERCADOPAGO_STARTING_PAGE_OFFSET)null else params.key?.apply {
                    offset?.minus(1)
                },
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}