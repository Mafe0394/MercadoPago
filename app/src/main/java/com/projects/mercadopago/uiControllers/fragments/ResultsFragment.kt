package com.projects.mercadopago.uiControllers.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.projects.mercadopago.adapters.ResultsAdapter
import com.projects.mercadopago.data.repository.ProductsRepository
import com.projects.mercadopago.databinding.FragmentResultsBinding
import com.projects.mercadopago.util.ProductClick
import com.projects.mercadopago.viewModels.ResultsViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class ResultsFragment : Fragment() {

    @Inject
    lateinit var repository: ProductsRepository

    private val viewModel by viewModels<ResultsViewModel> ()

    private lateinit var binding: FragmentResultsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentResultsBinding.inflate(inflater)

        setHasOptionsMenu(true)

        initViewModel()

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        initializeRecyclerView()

        return binding.root
    }

    private fun initViewModel() {
        val arguments = ResultsFragmentArgs.fromBundle(requireArguments())
        Timber.i("Query ${arguments.queryString}")
        viewModel.result.observe(viewLifecycleOwner, {
            viewModel.refreshProducts(it)
        })
        viewModel.startQueryResults(arguments.queryString)
    }

    private fun initializeRecyclerView() {
        // Initialize the RecyclerView
        binding.resultsRecyclerView.adapter = ResultsAdapter(ProductClick {
            // context is not around, we can safely discard this click since the Fragment is no
            // longer on the screen
            context?.packageManager ?: return@ProductClick

            findNavController().navigate(ResultsFragmentDirections.actionResultsFragmentToDetailFragment(
                it.productID))
            Timber.i("Product Title ${it.title}")
        })
        var i = 0
        binding.resultsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                val layoutManager = LinearLayoutManager::class.java.cast(recyclerView.layoutManager)
                val totalItemCount = layoutManager?.itemCount ?: 0
                val lastVisible = layoutManager?.findLastVisibleItemPosition()
                val endHasBeenReached = lastVisible?.plus(1) ?: 0 == totalItemCount
                if (totalItemCount > 0 && endHasBeenReached) {
                    //you have reached to the bottom of your recycler view
                    Timber.i("End of recyclerView ${i++}")
                }
            }
        })

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.resetViewModel()
        viewModel.result.removeObservers(viewLifecycleOwner)
        Timber.i("Back to Search Fragment")

        return NavigationUI.onNavDestinationSelected(item,
            view?.findNavController() as NavController) || super.onOptionsItemSelected(item)
    }


}
