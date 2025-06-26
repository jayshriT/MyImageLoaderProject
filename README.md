# MyImageLoaderProject
An Android image loading library named Impress, featuring memory caching, coroutine-based networking, and a clean fluent API. Includes a sample demo app for quick testing.


# Impress 🖼️

**Impress** is a lightweight image loading library for Android written in Kotlin. It supports memory caching, coroutine-based loading, and a fluent API.

## Features

- ✅ Load images from URL into `ImageView`
- 🧠 In-memory LRU caching
- ⚡ Coroutine + OkHttp-based networking
- 🎯 Placeholder and error image support
- 📱 Includes a sample demo app

## Usage

```kotlin
Impress.with(context)
    .load("https://picsum.photos/500")
    .placeholder(R.drawable.placeholder)
    .error(R.drawable.error)
    .into(imageView)
