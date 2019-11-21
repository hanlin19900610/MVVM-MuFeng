package com.mufeng.mvvmlib.ext

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/11/21 14:10
 * @描述
 */

inline fun <reified R>String.toJsonObject(): R?{
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val jsonAdapter = moshi.adapter<R>(R::class.java)
    return jsonAdapter.fromJson(this)
}

inline fun <reified R>R.toJson(): String{
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val jsonAdapter = moshi.adapter<R>(R::class.java)
    return jsonAdapter.toJson(this)
}

inline fun <reified R>String.toJsonList(): List<R>?{
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val listOfCardsType = Types.newParameterizedType(List::class.java, R::class.java)
    val jsonAdapter = moshi.adapter<List<R>>(listOfCardsType)
    return jsonAdapter.fromJson(this)
}

inline fun <reified R>List<R>.toJson(): String{
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val listOfCardsType = Types.newParameterizedType(List::class.java, R::class.java)
    val jsonAdapter = moshi.adapter<List<R>>(listOfCardsType)
    return jsonAdapter.toJson(this)
}