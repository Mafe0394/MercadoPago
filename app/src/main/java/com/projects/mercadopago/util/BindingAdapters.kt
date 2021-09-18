package com.projects.mercadopago.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.projects.mercadopago.R
import com.projects.mercadopago.adapters.ImageSliderAdapter
import com.projects.mercadopago.adapters.MarginItemDecoration
import com.projects.mercadopago.adapters.ProductPicturesAdapter
import com.projects.mercadopago.adapters.ResultsAdapter1
import com.projects.mercadopago.data.domain.Product
import com.projects.mercadopago.data.network.MercadoApiStatus
import me.relex.circleindicator.CircleIndicator3

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
    data: List<Product>?,
) {
//    val adapter = recyclerView.adapter as ResultsAdapter
    val adapter1:ResultsAdapter1=recyclerView.adapter as ResultsAdapter1

    // Adding separation between the recycler view Items
    recyclerView.addItemDecoration(
        MarginItemDecoration(
            recyclerView.context.resources.getDimensionPixelSize(R.dimen._2dp)
        )
    )
    // Tells the RecyclerView when a new list is available
//    adapter.submitList(data)
    adapter1.submitList(data)
}

@BindingAdapter("listImages")
fun bindImageRecyclerView(
    recyclerView: RecyclerView,
    data: List<String>?,
) {
    val adapter=recyclerView.adapter as ProductPicturesAdapter

    // Adding separation between the recycler view Items
    recyclerView.addItemDecoration(
        MarginItemDecoration(
            recyclerView.context.resources.getDimensionPixelSize(R.dimen._2dp)
        )
    )
    // Tells the RecyclerView when a new list is available
    adapter.submitList(data)
}

@BindingAdapter("listImages1")
fun bindImageRecyclerView1(
    viewPager: ViewPager2,
    data: List<String>?,
) {
    data?.let {
        viewPager.adapter=ImageSliderAdapter(it)
        viewPager.orientation=ViewPager2.ORIENTATION_HORIZONTAL
        val indicator=viewPager.findViewById<CircleIndicator3>(R.id.indicator)
        indicator.setViewPager(viewPager)
    }


    // Tells the RecyclerView when a new list is available
//    adapter.submitList(data)
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
