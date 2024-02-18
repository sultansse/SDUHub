package com.softwareit.sduhub.common.utils

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator

fun Any?.isNull() = this == null
fun Any?.isNotNull() = this != isNull()

val String.Companion.empty get() = ""

fun Context.vibratePhone() {
    val vibration = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    if (Build.VERSION.SDK_INT >= 26) {
        vibration.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))
    } else {
        vibration.vibrate(100)
    }
}