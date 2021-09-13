package com.projects.mercadopago.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.projects.mercadopago.R
import com.projects.mercadopago.adapters.MarginItemDecoration
import com.projects.mercadopago.adapters.ResultsAdapter
import com.projects.mercadopago.domain.ProductModel
import com.projects.mercadopago.network.MercadoApiStatus
import timber.log.Timber

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let { url ->
        // Converts the URL string (from the XML) to a URI object
        // The server requires to use secure scheme (HTTPS)
        val imgUri = url.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.broken_image)
            )
            .into(imgView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data: List<ProductModel>?
) {
    Timber.i("${recyclerView.id} ")
    val adapter = recyclerView.adapter as ResultsAdapter

    // Adding separation between the recycler view Items
    recyclerView.addItemDecoration(
        MarginItemDecoration(
            recyclerView.context.resources.getDimensionPixelSize(R.dimen._5dp)
        )
    )
    // Tells the RecyclerView when a new list is available
    adapter.submitList(data)
}

@BindingAdapter("appApiStatus")
fun bindStatus(
    statusImageView: ImageView,
    status: MercadoApiStatus?
) {
    when (status) {
        MercadoApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MercadoApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.no_connection_icon)
        }
        MercadoApiStatus.DONE -> statusImageView.visibility = View.GONE
        MercadoApiStatus.EMPTY_SEARCH-> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.no_results_icon)
        }

    }
}

@BindingAdapter("appApiSTexttatus")
fun bindTextStatus(
    statusTextView: TextView,
    status: MercadoApiStatus?
) {
    when (status) {
        MercadoApiStatus.LOADING -> {
            statusTextView.visibility = View.VISIBLE
            statusTextView.setText(R.string.loading_elllipsis)
        }
        MercadoApiStatus.ERROR -> {
            statusTextView.visibility = View.VISIBLE
            statusTextView.setText(R.string.something_went_wrong)
        }
        MercadoApiStatus.DONE -> statusTextView.visibility = View.GONE
        MercadoApiStatus.EMPTY_SEARCH-> {
            statusTextView.visibility = View.VISIBLE
            statusTextView.setText(R.string.we_couldnt_find_products_that_match_your_search_criteria)
        }

    }
}
