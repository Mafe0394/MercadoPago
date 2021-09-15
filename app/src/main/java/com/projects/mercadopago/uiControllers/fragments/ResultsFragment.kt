package com.projects.mercadopago.uiControllers.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.projects.mercadopago.R
import com.projects.mercadopago.adapters.ResultsAdapter
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

    //    private val viewModel by viewModels<TasksViewModel> {
//        TasksViewModelFactory(DefaultTasksRepository.getRepository(requireActivity().application))
//    }
//    private lateinit var viewModel: ResultsViewModel
    private lateinit var binding: FragmentResultsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentResultsBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arguments = ResultsFragmentArgs.fromBundle(requireArguments())
        Timber.i("Query ${arguments.queryString}")

//        viewModel = ViewModelProvider(this, ResultsViewModelFactory(query = arguments.queryString))
//            .get(ResultsViewModel::class.java)
        viewModel.startQueryResults(arguments.queryString)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        viewModel.products1.observe(viewLifecycleOwner, {
            Timber.i("Null or empty? ${it.isNullOrEmpty()} value $it")
        })

        // Initialize the RecyclerView
        binding.resultsRecyclerView.adapter = ResultsAdapter()
    }

}