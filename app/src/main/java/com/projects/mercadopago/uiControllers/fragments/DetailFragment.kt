package com.projects.mercadopago.uiControllers.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.projects.mercadopago.data.repository.ProductsRepository
import com.projects.mercadopago.databinding.FragmentDetailBinding
import com.projects.mercadopago.viewModels.DetailViewModel
import com.projects.mercadopago.viewModels.viewModelsFactory.DetailViewModelFactory
import timber.log.Timber


class DetailFragment : Fragment() {

    private lateinit var binding:FragmentDetailBinding

    private val viewModel by viewModels<DetailViewModel> {
        DetailViewModelFactory(ProductsRepository.getRepository(requireActivity().application))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding=FragmentDetailBinding.inflate(inflater)

        setHasOptionsMenu(true)

        initViewModel()

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    private fun initViewModel() {
        val arguments = DetailFragmentArgs.fromBundle(requireArguments())
        Timber.i("Query ${arguments.productId}")
        viewModel.getProductDetails(arguments.productId)
    }
}