package com.sanjeevdev.imagesearch.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sanjeevdev.imagesearch.models.ImageRootItem
import com.sanjeevdev.imagesearch.utils.Converters

@Database(entities = [ImageRootItem::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ImagesDatabase : RoomDatabase() {

    abstract fun getDao(): Dao

    companion object {
        private const val DATABASE_NAME = "ImagesDatabase"

        @Volatile
        var instance: ImagesDatabase? = null

        fun getInstance(context: Context): ImagesDatabase? {
            if (instance == null) {
                synchronized(ImagesDatabase::class.java) {
                    if (instance == null) {
                        instance =
                            Room.databaseBuilder(context, ImagesDatabase::class.java, DATABASE_NAME)
                                .build()
                    }
                }
            }
            return instance
        }
    }
}