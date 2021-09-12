package com.projects.mercadopago.uiControllers.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projects.mercadopago.R
import timber.log.Timber


class ResultsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val arguments=ResultsFragmentArgs.fromBundle(requireArguments())
        Timber.i(arguments.queryString)
        return inflater.inflate(R.layout.fragment_results, container, false)
    }

}