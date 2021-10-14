package com.projects.mercadopago.uiControllers.fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.projects.mercadopago.R
import com.projects.mercadopago.adapters.VisitedProductsAdapter
import com.projects.mercadopago.data.repository.ResultMercadoPago
import com.projects.mercadopago.databinding.FragmentSearchBinding
import com.projects.mercadopago.uiControllers.activities.MainActivity
import com.projects.mercadopago.util.ProductClick
import com.projects.mercadopago.viewModels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()

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

        initializeObservers()

        return binding.root
    }

    private fun initializeObservers() {
        viewModel.complete.observe(viewLifecycleOwner, {
            if(it is ResultMercadoPago.Success)
                viewModel.setLitVisitedProducts(it.data)
        })
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
                    // Reset SearchView
//                    viewModel.resetQuery()
                    // Go to Results Fragment to show results
                    viewModel.setIsExpanded(false)
                    if (this@SearchFragment.isVisible)
                        findNavController().navigate(
                            SearchFragmentDirections.actionSearchFragmentToResultsFragment(
                                p0 ?: ""
                            )
                        )

                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
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
        setSearchViewOnExpandListener(searchViewMenuItem)

    }

    private fun setSearchViewOnExpandListener(searchViewMenuItem: MenuItem) {
        searchViewMenuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                (activity as MainActivity).hideSoftKeyboard(hide = false)
                searchViewMenuItem.actionView.requestFocus()
                viewModel.setIsExpanded(isExpanded = true)
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                (activity as MainActivity).hideSoftKeyboard(hide = true)
                viewModel.setIsExpanded(isExpanded = false)
                return true
            }

        })
    }




}