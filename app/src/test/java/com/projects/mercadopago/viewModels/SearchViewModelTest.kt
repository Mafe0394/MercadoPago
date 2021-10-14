package com.projects.mercadopago.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.projects.mercadopago.MainCoroutineRule
import com.projects.mercadopago.data.source.FakeTestRepository
import com.projects.mercadopago.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Test
    fun `get three element list of visited Products, returns EmptyList false and list size of 3`() =
        runBlockingTest {
            val repository = FakeTestRepository()
            repository.getProduct("product1")
            repository.getProduct("product2")
            repository.getProduct("product3")

            val viewModel=SearchViewModel(repository,null)

            viewModel.getVisitedProductsFromDatabase()

            val emptyList=viewModel.emptyList.getOrAwaitValue()
            val results=viewModel.visitedProducts.getOrAwaitValue()

            assertThat(emptyList,`is`(false))
            assertThat(results?.size,`is`(3))
        }

    @Test
    fun `get empty list of visited products, returns EmptyList true`()
    = runBlockingTest {
        val viewModel=SearchViewModel(FakeTestRepository(),null)

        viewModel.getVisitedProductsFromDatabase()

        val emptyList=viewModel.emptyList.getOrAwaitValue()

        assertThat(emptyList,`is`(true))
    }

    @Test
    fun `Delete list of visited Products, returns EmptyList true`()
    = runBlockingTest {
        val repository = FakeTestRepository()
        repository.getProduct("product1")
        repository.getProduct("product2")
        repository.getProduct("product3")

        val viewModel=SearchViewModel(repository,null)

        viewModel.deleteVisitedProducts()

        val emptyList=viewModel.emptyList.getOrAwaitValue()

        assertThat(emptyList,`is`(true))
    }

   @Test
   fun `Set TextQuery, returns Query text`(){
       val viewModel=SearchViewModel(FakeTestRepository(),null)

       viewModel.setQuery("Test text")

       assertThat(viewModel.query.getOrAwaitValue(),`is`("Test text"))
   }

}