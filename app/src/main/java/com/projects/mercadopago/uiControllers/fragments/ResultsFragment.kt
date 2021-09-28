package com.projects.mercadopago.uiControllers.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.paging.LoadState
import com.projects.mercadopago.adapters.ResultsAdapter
import com.projects.mercadopago.databinding.FragmentResultsBinding
import com.projects.mercadopago.util.ProductClick
import com.projects.mercadopago.viewModels.ResultsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class ResultsFragment : Fragment() {

    private val productsAdapter by lazy {
        ResultsAdapter(ProductClick {
            // context is not around, we can safely discard this click since the Fragment is no
            // longer on the screen
            context?.packageManager ?: return@ProductClick

            findNavController().navigate(ResultsFragmentDirections.actionResultsFragmentToDetailFragment(
                it.productID))
        })
    }

    private val viewModel by viewModels<ResultsViewModel> ()

    private lateinit var binding: FragmentResultsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentResultsBinding.inflate(inflater)

        setHasOptionsMenu(true)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        initializeRecyclerView()

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
            view?.findNavController() as NavController) || super.onOptionsItemSelected(item)
    }

    private fun initializeRecyclerView() {
        // initial recyclerView
        binding.resultsRecyclerView.adapter = productsAdapter
        initObservers()
        lifecycleScope.launch {
            productsAdapter.loadStateFlow.collectLatest { loadStates ->
                Timber.i("Loading ${loadStates.refresh is LoadState.Loading}")
                Timber.i("Retry ${loadStates.refresh !is LoadState.Loading}")
                Timber.i("Error ${loadStates.refresh is LoadState.Error}")
            }
        }
//            // Only emit when REFRESH LoadState for RemoteMediator changes.
//            .distinctUntilChangedBy { it.refresh }
//            // Only react to cases where Remote REFRESH completes i.e., NotLoading.
//            .map { it.refresh is LoadState.NotLoading }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getProductsList().observe(viewLifecycleOwner, {
                productsAdapter.submitData(lifecycle, it)
            })
        }
    }

}
