package com.sanjeevdev.imagesearch.models

import java.io.Serializable

data class Urls(
    val full: String,
    val raw: String,
    val regular: String,
    val small: String,
    val thumb: String
): Serializable