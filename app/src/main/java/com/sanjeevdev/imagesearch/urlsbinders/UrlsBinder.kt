package com.sanjeevdev.imagesearch.urlsbinders

import com.sanjeevdev.imagesearch.utils.URLs

class UrlsBinder {
    companion object {
        fun bindUrl(query: String, page: Int): String {
            if (query.isNotEmpty()) {
                return "${URLs.BASE_URL}photos/?client_id=${URLs.CLIENT_ID}&query=${query}&page=${page}&per_page=25"
            }else{
                return "${URLs.BASE_URL}photos/?client_id=${URLs.CLIENT_ID}&page=${page}&per_page=25"
            }
        }
    }
}