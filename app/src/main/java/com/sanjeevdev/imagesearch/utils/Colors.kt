package com.sanjeevdev.imagesearch.utils

import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi

class Colors {
    companion object {
        fun getContrastColor(color: Int): Int {
            val y =
                ((299 * Color.red(color) + 587 * Color.green(color) + 114 * Color.blue(color)) / 1000).toDouble()
            return if (y >= 128) Color.BLACK else Color.WHITE
        }
    }
}