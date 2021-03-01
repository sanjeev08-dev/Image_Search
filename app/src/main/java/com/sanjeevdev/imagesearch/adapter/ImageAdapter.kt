package com.sanjeevdev.imagesearch.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sanjeevdev.imagesearch.R
import com.sanjeevdev.imagesearch.callbacks.ImagesDiffCallback
import com.sanjeevdev.imagesearch.listener.ObjectClickListener
import com.sanjeevdev.imagesearch.models.ImageRootItem
import com.sanjeevdev.imagesearch.utils.Colors
import kotlinx.android.synthetic.main.item_images.view.*

class ImageAdapter(
    listImages: ArrayList<ImageRootItem>,
    private val context: Context,
    private val objectClickListener: ObjectClickListener
) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private val imagesList = mutableListOf<ImageRootItem>()

    init {
        imagesList.addAll(listImages)
    }

    inner class ImageViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun setImageDetails(imageResult: ImageRootItem, context: Context) {

            Glide.with(view.item_images).load(imageResult.urls.thumb).into(view.item_images)
            view.item_text.setBackgroundColor(Color.parseColor(imageResult.color))
            view.item_text.text = "${imageResult.width} x ${imageResult.height}"
            view.item_text.setTextColor(ColorStateList.valueOf(Colors.getContrastColor(Color.parseColor(imageResult.color))))
            view.item_card.setStrokeColor(ColorStateList.valueOf(Color.parseColor(imageResult.color)))
            view.item_card.setOnClickListener {
                objectClickListener.onClick(imageResult)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_images, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.setImageDetails(imagesList[position], context)
    }

    override fun getItemCount() = imagesList.size

    fun refreshImages(imagesList: List<ImageRootItem>) {
        val diffCallback = ImagesDiffCallback(this.imagesList, imagesList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.imagesList.clear()
        this.imagesList.addAll(imagesList)
        diffResult.dispatchUpdatesTo(this)
    }

}