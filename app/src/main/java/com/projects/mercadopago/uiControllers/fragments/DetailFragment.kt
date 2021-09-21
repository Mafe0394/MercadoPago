package com.projects.mercadopago.uiControllers.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.projects.mercadopago.adapters.ImageSliderAdapter
import com.projects.mercadopago.data.repository.ProductsRepository
import com.projects.mercadopago.databinding.FragmentDetailBinding
import com.projects.mercadopago.util.observeOnce
import com.projects.mercadopago.viewModels.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    @Inject lateinit var repository:ProductsRepository

    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater)

        setHasOptionsMenu(true)

        initViewModel()

        initializeRecyclerView()

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }


    private fun initializeRecyclerView() {
        viewModel.productDetail.observeOnce(viewLifecycleOwner, { product ->
            product?.imagesUrls.let { imageList ->
                binding.imageSliderViewPager.apply {
                    adapter = ImageSliderAdapter(imageList ?: ArrayList())
                    orientation = ViewPager2.ORIENTATION_HORIZONTAL
                    binding.indicator.setViewPager(this)
                }
            }
        })

//        binding.indicator.setViewPager(binding.imageSliderViewPager)
    }

    private fun initViewModel() {
        val arguments = DetailFragmentArgs.fromBundle(requireArguments())
        Timber.i("Query ${arguments.productId}")
        viewModel.getProductDetails(arguments.productId)
    }
}