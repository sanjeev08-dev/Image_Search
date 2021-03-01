package com.sanjeevdev.imagesearch.callbacks

import androidx.recyclerview.widget.DiffUtil
import com.sanjeevdev.imagesearch.models.ImageRootItem

class ImagesDiffCallback(
    private val oldList: List<ImageRootItem>,
    private val newList: List<ImageRootItem>
) : DiffUtil.Callback() {
    override fun getOldListSize()= oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].alt_description == newList[newItemPosition].alt_description
    }
}