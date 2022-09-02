package gr.dipae.googlemapsearcher.util

import android.content.res.Resources

val Resources.screenHeight: Int
    get() = displayMetrics.heightPixels

val Resources.screenWidth: Int
    get() = displayMetrics.widthPixels

fun Resources.getStatusBarHeight(): Int {
    var statusBarHeight = 0
    val resourceId = getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        statusBarHeight = getDimensionPixelSize(resourceId)
    }
    return statusBarHeight
}