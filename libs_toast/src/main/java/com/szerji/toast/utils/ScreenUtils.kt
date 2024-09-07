package com.szerji.toast.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.TypedValue

/**
 * description
 * use
 * param
 * return
 *
 * @author JetQiao
 * @date 2024/9/6 21:59
 */
object ScreenUtils {

    fun dpToPx(context: Context, dp: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), context.resources.displayMetrics).toInt()
    }

    @SuppressLint("InternalInsetResource")
    fun getStatusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (resourceId > 0) {
            context.resources.getDimensionPixelSize(resourceId)
        } else {
            0
        }
    }
}