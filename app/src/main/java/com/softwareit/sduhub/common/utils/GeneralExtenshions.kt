package com.softwareit.sduhub.common.utils

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun Any?.isNull() = this == null
fun Any?.isNotNull() = this != isNull()

val String.Companion.empty get() = ""

fun Context.vibratePhone() {
    val vibration = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    vibration.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))
}

fun getFormattedTime(dateTime: LocalDateTime = LocalDateTime.now()): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")
    return dateTime.format(formatter)
}