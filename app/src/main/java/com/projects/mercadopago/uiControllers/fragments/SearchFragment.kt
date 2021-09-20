package com.projects.mercadopago.uiControllers.fragments

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.projects.mercadopago.R
import com.projects.mercadopago.adapters.VisitedProductsAdapter
import com.projects.mercadopago.data.repository.ProductsRepository
import com.projects.mercadopago.databinding.FragmentSearchBinding
import com.projects.mercadopago.util.ProductClick
import com.projects.mercadopago.viewModels.SearchViewModel
import com.projects.mercadopago.viewModels.viewModelsFactory.SearchViewModelFactory
import timber.log.Timber

class SearchFragment : Fragment() {

    private val viewModel by viewModels<SearchViewModel> {
        SearchViewModelFactory(ProductsRepository.getRepository(requireActivity().application))
    }

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater)


        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        initializeRecyclerView()

        viewModel.complete.observe(viewLifecycleOwner, {
            viewModel.setLitVisitedProducts(it)
        })

        return binding.root
    }

    private fun initializeRecyclerView() {
        binding.productsRecyclerView.adapter = VisitedProductsAdapter(ProductClick {
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToDetailFragment(
                it.productID))
        })
    }

    override fun onResume() {
        viewModel.getVisitedProductsFromDatabase()
        super.onResume()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)

        // Check if searchView is expanded and keep it after rotation
        keepExpandedAfterRotation(menu)

        (menu.findItem(R.id.search)?.actionView as SearchView).apply {
            // Open SearchView when click on optionsMenu search icon
            isIconifiedByDefault = false
            onActionViewExpanded()
            // Set query value for persistence when screen rotation
            setQuery(viewModel.query.value, false)

            this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    Timber.i("Submited $p0")
                    // Reset SearchView
//                    viewModel.resetQuery()
                    viewModel.setIsExpanded(false)

                    // Go to Results Fragment to show results
                    findNavController().navigate(
                        SearchFragmentDirections.actionSearchFragmentToResultsFragment(
                            p0 ?: ""
                        )
                    )
                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    Timber.i("Text changed $p0")
                    // Update Query in ViewModel
                    viewModel.setQuery(p0)
                    return true
                }

            })
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun keepExpandedAfterRotation(menu: Menu) {
        val searchViewMenuItem = menu.findItem(R.id.search)
        if (viewModel.isExpanded.value == true)
            searchViewMenuItem.expandActionView()
        searchViewMenuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                Timber.i("expanded")

                viewModel.setIsExpanded(true)
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                Timber.i("collased")
                viewModel.setIsExpanded(false)

                return true
            }

        })
    }


}