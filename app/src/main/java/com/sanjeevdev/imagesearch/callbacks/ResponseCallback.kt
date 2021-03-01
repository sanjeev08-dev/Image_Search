package com.sanjeevdev.imagesearch.callbacks

import com.android.volley.VolleyError
import com.sanjeevdev.imagesearch.models.ImageRoot

interface ResponseCallback {
    fun onSuccess(imagesRoot: ImageRoot) {

    }

    fun onFailure(volleyError: VolleyError) {

    }
}