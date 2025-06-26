package com.impress.learn

import android.graphics.Bitmap
import androidx.collection.LruCache

object MemoryCache {
    private val cache: LruCache<String, Bitmap> by lazy {
        val maxSize = (Runtime.getRuntime().maxMemory() / 1024).toInt() / 8
        object : LruCache<String, Bitmap>(maxSize) {}
    }

    fun get(key: String): Bitmap? = cache.get(key)
    fun put(key: String, bitmap: Bitmap) = cache.put(key, bitmap)
}