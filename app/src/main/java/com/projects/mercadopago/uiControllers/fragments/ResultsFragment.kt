package com.projects.mercadopago.uiControllers.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.projects.mercadopago.R
import com.projects.mercadopago.adapters.ResultsAdapter
import com.projects.mercadopago.adapters.ResultsAdapter1
import com.projects.mercadopago.databinding.FragmentResultsBinding
import com.projects.mercadopago.domain.Product
import com.projects.mercadopago.repository.ProductsRepository
import com.projects.mercadopago.util.setToolbarTitle
import com.projects.mercadopago.viewModels.ResultsViewModel
import com.projects.mercadopago.viewModels.viewModelsFactory.ResultsViewModelFactory
import timber.log.Timber


class ResultsFragment : Fragment() {

    private val viewModel by viewModels<ResultsViewModel> {
        ResultsViewModelFactory(ProductsRepository.getRepository(requireActivity().application))
    }

    private lateinit var binding: FragmentResultsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentResultsBinding.inflate(inflater)

        setHasOptionsMenu(true)

        val arguments = ResultsFragmentArgs.fromBundle(requireArguments())
        Timber.i("Query ${arguments.queryString}")

        viewModel.startQueryResults(arguments.queryString)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

//        viewModel.products.observe(viewLifecycleOwner){
//
//        }

        // Initialize the RecyclerView
        binding.resultsRecyclerView.adapter = ResultsAdapter1()

        return binding.root
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.resetViewModel()
        Timber.i("Back button ${viewModel.isEmptySearch.value}")

        return NavigationUI.onNavDestinationSelected(item,
            view?.findNavController() as NavController) || super.onOptionsItemSelected(item)
    }


}
