package com.sanjeevdev.imagesearch.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sanjeevdev.imagesearch.callbacks.ResponseCallback
import com.sanjeevdev.imagesearch.models.ImageRootItem
import com.sanjeevdev.imagesearch.repository.Repository

class ImagesViewModel : ViewModel() {
    fun getImages(url: String, context: Context,responseCallback: ResponseCallback) {
        Repository.getImages(url, context,responseCallback)
    }
    fun insertImage(context: Context,imageRootItem: ImageRootItem){
        Repository.insert(context,imageRootItem)
    }
    fun getAllNotes(context: Context) : LiveData<List<ImageRootItem>>? {
        return Repository.getAllNotes(context)
    }
}