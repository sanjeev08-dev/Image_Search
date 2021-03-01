package com.sanjeevdev.imagesearch.models

import androidx.room.TypeConverters
import java.io.Serializable

data class Sponsorship(
    val impression_urls: List<String>,
    @TypeConverters(Sponsor::class)
    val sponsor: Sponsor,
    val tagline: String,
    val tagline_url: String
): Serializable