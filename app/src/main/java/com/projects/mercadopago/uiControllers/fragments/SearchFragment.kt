package com.projects.mercadopago.uiControllers.fragments

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.projects.mercadopago.R
import com.projects.mercadopago.databinding.FragmentSearchBinding
import com.projects.mercadopago.viewModels.SearchViewModel
import com.projects.mercadopago.viewModels.viewModelsFactory.SearchViewModelFactory
import timber.log.Timber

class SearchFragment : Fragment() {

    private val searchViewModel by viewModels<SearchViewModel> {
        SearchViewModelFactory()
    }

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        binding = FragmentSearchBinding.bind(view).apply {
            viewModel = searchViewModel
        }
        binding.lifecycleOwner = this.viewLifecycleOwner
        setHasOptionsMenu(true)

        return view
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)

        // Check if searchView is expanded and keep it after rotation
        keepExpandedAfterRotation(menu)

        // Associate searchable configuration with the SearchView
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search)?.actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
            // Open SearchView when click on optionsMenu search icon
            isIconifiedByDefault = false
            onActionViewExpanded()
            // Set query value for persistence when screen rotation
            setQuery(searchViewModel.query.value, false)

            this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    Timber.i("Submited $p0")
                    // Reset SearchView
//                    searchViewModel.resetQuery()
                    searchViewModel.setIsExpanded(false)

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
                    searchViewModel.setQuery(p0)
                    return true
                }

            })
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun keepExpandedAfterRotation(menu: Menu) {
        val searchViewMenuItem = menu.findItem(R.id.search)
        if (searchViewModel.isExpanded.value == true)
            searchViewMenuItem.expandActionView()
        searchViewMenuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                Timber.i("expanded")
                searchViewModel.setIsExpanded(true)
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                Timber.i("collased")
                searchViewModel.setIsExpanded(false)
                return true
            }

        })
    }


}