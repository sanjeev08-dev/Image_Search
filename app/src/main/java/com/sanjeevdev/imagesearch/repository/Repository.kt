package com.sanjeevdev.imagesearch.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.sanjeevdev.imagesearch.callbacks.ResponseCallback
import com.sanjeevdev.imagesearch.models.ImageRoot
import com.sanjeevdev.imagesearch.models.ImageRootItem
import com.sanjeevdev.imagesearch.room.ImagesDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class Repository {
    companion object {
        fun getImages(url: String, context: Context,responseCallback: ResponseCallback) {
            CoroutineScope(IO).launch {
                val queue = Volley.newRequestQueue(context)
                val stringRequest = StringRequest(
                    Request.Method.GET, url,
                    { response ->
                        val imagesRoot = Gson().fromJson(response, ImageRoot::class.java)
                        responseCallback.onSuccess(imagesRoot)
                    },
                    {
                        Log.e("TAG", "getImages: " + it.localizedMessage)
                    })

                queue.add(stringRequest)
            }
        }

        private var imageDatabase: ImagesDatabase? = null
        fun initializeDB(context: Context): ImagesDatabase? {
            return ImagesDatabase.getInstance(context)
        }

        fun insert(context: Context, imageRootItem: ImageRootItem) {
            imageDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                imageDatabase?.getDao()?.insert(imageRootItem)
            }
        }

        fun getAllNotes(context: Context): LiveData<List<ImageRootItem>>? {
            imageDatabase = initializeDB(context)
            return imageDatabase?.getDao()?.getAllNotes()
        }
    }
}