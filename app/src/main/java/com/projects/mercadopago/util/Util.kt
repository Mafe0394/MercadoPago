package com.projects.mercadopago.util

import androidx.fragment.app.Fragment
import com.projects.mercadopago.uiControllers.activities.MainActivity

fun Fragment.setToolbarTitle(title: String) {
    (activity as MainActivity).supportActionBar?.title = title
}
