package com.projects.mercadopago.util

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.projects.mercadopago.R
import com.projects.mercadopago.adapters.MarginItemDecoration
import com.projects.mercadopago.adapters.VisitedProductsAdapter
import com.projects.mercadopago.data.domain.Product
import com.projects.mercadopago.data.network.MercadoApiStatus

@BindingAdapter("imageUrl")
fun ImageView.bindImage(imgUrl: String?) {
    imgUrl?.let { url ->
        // Converts the URL string (from the XML) to a URI object
        // The server requires to use secure scheme (HTTPS)
        val imgUri = url.toUri().buildUpon().scheme("https").build()
        Glide.with(context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_image)
                    .error(R.drawable.broken_image)
            )
            .into(this)
    }
}

@BindingAdapter("listVisitedProducts")
fun RecyclerView.bindVisitedProducts(data: List<Product>?) {
    val adapter = adapter as VisitedProductsAdapter
    addItemDecoration(MarginItemDecoration(context.resources.getDimensionPixelSize(R.dimen._2dp)))
    adapter.submitList(data)
}

@BindingAdapter("appApiStatus")
fun ImageView.bindStatus(
    status: MercadoApiStatus?,
) {
    when (status) {
        MercadoApiStatus.LOADING -> {
            visibility = View.VISIBLE
            setImageResource(R.drawable.loading_animation)
        }
        MercadoApiStatus.ERROR -> {
            visibility = View.VISIBLE
            setImageResource(R.drawable.no_connection_icon)
        }
        MercadoApiStatus.DONE -> visibility = View.GONE
    }
}

@BindingAdapter("appApiSTextStatus")
fun TextView.bindTextStatus(
    status: MercadoApiStatus?,
) {
    when (status) {
        MercadoApiStatus.LOADING -> {
            visibility = View.VISIBLE
            setText(R.string.loading_elllipsis)
        }
        MercadoApiStatus.ERROR -> {
            visibility = View.VISIBLE
            setText(R.string.something_went_wrong)
        }
        MercadoApiStatus.DONE -> visibility = View.GONE

    }
}

@BindingAdapter("visibility")
fun TextView.visibility(
    isEmpty: Boolean,
) {
    visibility = if (isEmpty) View.VISIBLE else View.GONE
}

@BindingAdapter("priceInCop")
fun TextView.priceFormatted(
    price: Long?,
) {
    text = context.getString(R.string.display_price, price)
}

@BindingAdapter("resultsFor")
fun TextView.queryFormatted(
    query: String,
) {
    text = context.getString(R.string.results_for, query)
}

@BindingAdapter("visibility")
fun Button.visibility(visible: Boolean){
    visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("visibility")
fun RecyclerView.visibility(visible: Boolean){
    visibility = if (visible) View.VISIBLE else View.GONE
}
