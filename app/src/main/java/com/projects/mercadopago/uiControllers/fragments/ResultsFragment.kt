package com.projects.mercadopago.uiControllers.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.projects.mercadopago.R
import com.projects.mercadopago.databinding.FragmentResultsBinding
import com.projects.mercadopago.databinding.FragmentSearchBinding
import com.projects.mercadopago.viewModels.ResultsViewModel
import com.projects.mercadopago.viewModels.SearchViewModel
import com.projects.mercadopago.viewModels.viewModelsFactory.ResultsViewModelFactory
import com.projects.mercadopago.viewModels.viewModelsFactory.SearchViewModelFactory
import timber.log.Timber


class ResultsFragment : Fragment() {

    private val viewModel by viewModels<ResultsViewModel> {
        ResultsViewModelFactory()
    }
    private lateinit var binding: FragmentResultsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentResultsBinding.inflate(inflater)

        binding.viewModel=viewModel
        binding.lifecycleOwner=this.viewLifecycleOwner

        val arguments=ResultsFragmentArgs.fromBundle(requireArguments())
        Timber.i("Query ${arguments.queryString}")
        return binding.root
    }

}