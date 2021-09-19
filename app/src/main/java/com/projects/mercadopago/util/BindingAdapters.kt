package com.projects.mercadopago.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.projects.mercadopago.R
import com.projects.mercadopago.adapters.MarginItemDecoration
import com.projects.mercadopago.adapters.ResultsAdapter
import com.projects.mercadopago.adapters.VisitedProductsAdapter
import com.projects.mercadopago.data.domain.Product
import com.projects.mercadopago.data.network.MercadoApiStatus

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
fun RecyclerView.bindRecyclerView(
    data: List<Product>?,
) {
    val adapter = adapter as ResultsAdapter
    addItemDecoration(MarginItemDecoration(context.resources.getDimensionPixelSize(R.dimen._2dp)))
    adapter.submitList(data)
}

@BindingAdapter("listVisitedProducts")
fun RecyclerView.bindVisitedProducts(data: List<Product>?) {
    val adapter = adapter as VisitedProductsAdapter
    addItemDecoration(MarginItemDecoration(context.resources.getDimensionPixelSize(R.dimen._2dp)))
    val x = arrayListOf(Product("Gato", "algo", 40000, "Error"),
        Product("Gato", "algo", 40000, "Error"),
        Product("Gato", "algo", 40000, "Error"),
        Product("Gato", "algo", 40000, "Error"))
    adapter.submitList(data)
}

@BindingAdapter("appApiStatus")
fun bindStatus(
    statusImageView: ImageView,
    status: MercadoApiStatus?,
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
    }
}

@BindingAdapter("appApiSTextStatus")
fun bindTextStatus(
    statusTextView: TextView,
    status: MercadoApiStatus?,
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

    }
}

@BindingAdapter("emptySearchText")
fun isEmptySearchText(
    textView: TextView,
    isEmpty: Boolean,
) {

    if (isEmpty)
        textView.visibility = View.VISIBLE
    else
        textView.visibility = View.GONE
}

@BindingAdapter("priceInCop")
fun priceFormatted(
    textView: TextView,
    price: Long?,
) {
    textView.text = textView.context.getString(R.string.display_price, price)
}

@BindingAdapter("resultsFor")
fun queryFormatted(
    textView: TextView,
    query: String,
) {
    textView.text = textView.context.getString(R.string.results_for, query)
}
