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
import androidx.navigation.fragment.NavHostFragment
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

        // Set NavController with NavHostFragment
        val navHostFragment=supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        NavigationUI.setupActionBarWithNavController(this,navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        // Set up only UpButton on AppBar
        return navController.navigateUp()

    }

    fun setActionBarTitle(title:String){
        supportActionBar?.title=title
    }
}