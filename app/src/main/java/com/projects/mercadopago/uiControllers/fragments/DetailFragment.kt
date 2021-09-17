package com.projects.mercadopago.uiControllers.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.projects.mercadopago.R
import com.projects.mercadopago.databinding.FragmentDetailBinding
import com.projects.mercadopago.viewModels.DetailViewModel
import com.projects.mercadopago.viewModels.viewModelsFactory.DetailViewModelFactory


class DetailFragment : Fragment() {

    private lateinit var binding:FragmentDetailBinding

    private val viewModel by viewModels<DetailViewModel> {
        DetailViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentDetailBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root
    }
}