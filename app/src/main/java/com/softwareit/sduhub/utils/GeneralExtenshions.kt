package com.softwareit.sduhub.utils

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun Any?.isNull() = this == null
fun Any?.isNotNull() = this != null

val String.Companion.empty get() = ""

fun Context.vibratePhone() {
    val vibration = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    vibration.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))
}


const val DATE_TIME_FORMAT = "HH:mm:ss dd/MM/yyyy"


fun getFormattedTime(format: String = DATE_TIME_FORMAT, dateTime: LocalDateTime = LocalDateTime.now()): String {
    val formatter = DateTimeFormatter.ofPattern(format)
    return dateTime.format(formatter)
}