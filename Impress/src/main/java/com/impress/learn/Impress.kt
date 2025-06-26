package com.impress.learn

import android.content.Context

class Impress private constructor(
    private val context: Context
) {
    companion object {
        fun with(context: Context) = Impress(context)
    }

    fun load(url: String) = RequestBuilder(context, url)
}

