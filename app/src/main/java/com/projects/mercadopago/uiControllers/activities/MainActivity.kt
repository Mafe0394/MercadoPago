package com.projects.mercadopago.uiControllers.activities

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.TextView
import com.projects.mercadopago.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent?){
        if (Intent.ACTION_SEARCH==intent?.action){
            val query=intent.getStringExtra(SearchManager.QUERY)
            // Use the query to search your data somehow
            val textView=findViewById<TextView>(R.id.textView)
            textView.text=query
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu,menu)

        // Associate searchable configuration with the SearchView
        val searchManager=getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu?.findItem(R.id.search)?.actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }
        return true
    }
}