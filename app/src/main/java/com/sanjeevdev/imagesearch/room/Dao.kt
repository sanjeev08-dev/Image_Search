package com.sanjeevdev.imagesearch.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sanjeevdev.imagesearch.models.ImageRootItem

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(imageRootItem: ImageRootItem)

    @Query("SELECT * FROM SavedImages ORDER BY id DESC")
    fun getAllNotes() : LiveData<List<ImageRootItem>>
}