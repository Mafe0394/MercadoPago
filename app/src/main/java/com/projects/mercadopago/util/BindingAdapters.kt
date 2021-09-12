package com.projects.mercadopago.util

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.projects.mercadopago.R

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

//@BindingAdapter("listData")
//fun bindRecyclerView(
//    recyclerView: RecyclerView,
//    data: List<MarsProperty>?
//) {
//    val adapter = recyclerView.adapter as PhotoGridAdapter
//    // Tells the RecyclerView when a new list is available
//    adapter.submitList(data)
//}

//@BindingAdapter("marsApiStatus")
//fun bindStatus(
//    statusImageView: ImageView,
//    status: MarsApiStatus?
//) {
//    when (status) {
//        MarsApiStatus.LOADING -> {
//            statusImageView.visibility = View.VISIBLE
//            statusImageView.setImageResource(R.drawable.loading_animation)
//        }
//        MarsApiStatus.ERROR -> {
//            statusImageView.visibility = View.VISIBLE
//            statusImageView.setImageResource(R.drawable.ic_connection_error)
//        }
//        MarsApiStatus.DONE -> statusImageView.visibility = View.GONE
//
//
//    }
//}