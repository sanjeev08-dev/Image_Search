package com.sanjeevdev.imagesearch.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.sanjeevdev.imagesearch.utils.Converters
import java.io.Serializable


@Entity(tableName = "SavedImages")
data class ImageRootItem(
    val alt_description: String,
    val blur_hash: String,

    @TypeConverters(Converters::class)
    val categories: List<Any>,

    val color: String,
    val created_at: String,

    @TypeConverters(Converters::class)
    val current_user_collections: List<Any>,

    val description: String,
    val height: Int,

    @PrimaryKey
    val id: String,
    val liked_by_user: Boolean,
    val likes: Int,

    @TypeConverters(Converters::class)
    val links: Links,

    val promoted_at: String,
    @TypeConverters(Converters::class)
    val sponsorship: Sponsorship,

    val updated_at: String,

    @TypeConverters(Urls::class)
    val urls: Urls,

    @TypeConverters(User::class)
    val user: User,

    val width: Int
):Serializable