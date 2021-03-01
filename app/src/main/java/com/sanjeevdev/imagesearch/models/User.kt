package com.sanjeevdev.imagesearch.models

import androidx.room.TypeConverters
import java.io.Serializable

data class User(
    val accepted_tos: Boolean,

    @TypeConverters(Any::class)
    val bio: Any,

    val first_name: String,
    val id: String,

    @TypeConverters(Any::class)
    val instagram_username: Any,

    val last_name: String,

    @TypeConverters(LinksXX::class)
    val links: LinksXX,

    @TypeConverters(Any::class)
    val location: Any,

    val name: String,

    @TypeConverters(Any::class)
    val portfolio_url: Any,

    @TypeConverters(ProfileImageX::class)
    val profile_image: ProfileImageX,

    val total_collections: Int,
    val total_likes: Int,
    val total_photos: Int,

    @TypeConverters(Any::class)
    val twitter_username: Any,

    val updated_at: String,
    val username: String
): Serializable