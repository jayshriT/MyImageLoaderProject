package com.impress.learn

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class RequestBuilder constructor(
    private val context: Context,
    private val url: String
) {
    private var placeHolderRes: Int? = null
    private var errorRes: Int? = null

    fun placeHolder(resId: Int) : RequestBuilder {
        placeHolderRes = resId
        return this
    }

    fun error(resId: Int) : RequestBuilder {
        errorRes = resId
        return this
    }

    fun into(imageView: ImageView) {
        placeHolderRes?.let {
            imageView.setImageResource(it)
        }

        val cachedBitmap = MemoryCache.get(url)
        if(cachedBitmap != null) {
            imageView.setImageBitmap(cachedBitmap)
            return
        }
        MainScope().launch {
            val bitmap = downloadImage(url)
            if (bitmap != null) {
                MemoryCache.put(url, bitmap)
                imageView.setImageBitmap(bitmap)
            } else {
                errorRes?.let { imageView.setImageResource(it) }
            }
        }
    }

    private suspend fun downloadImage(url: String): Bitmap? = withContext(Dispatchers.IO) {
        try {
            val client = OkHttpClient()
            val request = Request.Builder().url(url).build()
            val response = client.newCall(request).execute()
            val inputStream = response.body?.byteStream()
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}