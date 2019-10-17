package com.mufeng.mvvmlib.ext

import android.content.Context
import android.widget.Toast
import com.mufeng.mvvmlib.basic.BaseApplication

/**
 * @author MuFeng-T
 * @createTime 2019-10-17
 * @details
 */

fun Context.toast(value: String) = toast { value }

inline fun Context.toast(value: () -> String) =
    Toast.makeText(this, value(), Toast.LENGTH_SHORT).show()

inline fun toast(value: () -> String): Unit =
    BaseApplication.CONTEXT.toast(value)