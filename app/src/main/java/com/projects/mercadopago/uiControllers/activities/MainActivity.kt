package com.projects.mercadopago.uiControllers.activities

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.projects.mercadopago.R
import com.projects.mercadopago.uiControllers.fragments.ResultsFragmentDirections
import com.projects.mercadopago.uiControllers.fragments.SearchFragment
import com.projects.mercadopago.uiControllers.fragments.SearchFragmentDirections
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var navController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Set up UpButton on appBar and hamburger button(optional)
        navController=this.findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this,navController)
//        handleIntent(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        // Set up only UpButton on AppBar
        return navController.navigateUp()

    }

//    override fun onNewIntent(intent: Intent?) {
//        super.onNewIntent(intent)
//        setIntent(intent)
//        handleIntent(intent)
//    }
//
//    private fun handleIntent(intent: Intent?) {
//        if (Intent.ACTION_SEARCH == intent?.action) {
//            val query = intent.getStringExtra(SearchManager.QUERY)
//            // Use the query to search your data somehow
//            findNavController(R.id.nav_host_fragment).navigate(
//                SearchFragmentDirections.actionSearchFragmentToResultsFragment(
//                    query ?: ""
//                )
//            )
//        }
//    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.options_menu, menu)
//
//        // Associate searchable configuration with the SearchView
//        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        searchManager.setOnDismissListener {
//            Timber.i("on dismiss Listener")
//        }
//        (menu?.findItem(R.id.search)?.actionView as SearchView).apply {
//            setSearchableInfo(searchManager.getSearchableInfo(componentName))
//            this.setOnCloseListener {
//                Timber.i("on Close Listener")
//                false }
//            this.setOnSearchClickListener {
//                Timber.i("on search Listener")
//            }
//        }
//
//        return true
//    }



}