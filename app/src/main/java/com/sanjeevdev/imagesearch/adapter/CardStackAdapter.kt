package com.sanjeevdev.imagesearch.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.sanjeevdev.imagesearch.R
import com.sanjeevdev.imagesearch.models.ImageRootItem
import kotlinx.android.synthetic.main.item_viewed_image.view.*

class CardStackAdapter(private var imagesList: ArrayList<ImageRootItem>, val context: Context) :
    RecyclerView.Adapter<CardStackAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun setImageDetails(imageResult: ImageRootItem) {
            view.loadingIndicator.visibility = View.VISIBLE
            Log.e("CardStackAdapter", "setImageDetails: " + imageResult.color)

            Glide.with(view.item_images_card).load(imageResult.urls.thumb).into(view.item_images_card)
            Glide.with(view.item_images_card).load(imageResult.urls.full).listener(object :
                RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Glide.with(view.item_images_card).load(imageResult.urls.thumb).into(view.item_images_card)
                    Toast.makeText(context, e!!.localizedMessage, Toast.LENGTH_LONG).show()
                    Log.e("TAG", "onLoadFailed: $e")
                    view.loadingIndicator.visibility = View.VISIBLE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    view.loadingIndicator.visibility = View.GONE
                    return false
                }

            }).into(view.item_images_card)
            view.item_images_description.text = imageResult.alt_description
            view.item_images_title.text = imageResult.user.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_viewed_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.setImageDetails(imagesList[position])
    }

    override fun getItemCount() = imagesList.size

    fun setSpots(imagesList: ArrayList<ImageRootItem>) {
        this.imagesList = imagesList
    }

    fun getSpots(): List<ImageRootItem> {
        return imagesList
    }

}