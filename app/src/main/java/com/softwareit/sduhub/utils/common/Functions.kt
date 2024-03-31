package com.softwareit.sduhub.utils.common

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.content.ContextCompat


fun openGmail(context: Context) {

    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf("recipient@stu.sdu.edu.kz"))
        putExtra(Intent.EXTRA_SUBJECT, "Subject")
        putExtra(Intent.EXTRA_TEXT, "Hello Teacher, I am ......")
    }

    try {
        ContextCompat.startActivity(context, intent, null)
    } catch (e: Exception) {
        e.printStackTrace()
        Toast.makeText(
            context,
            "Not found any email client to open the email",
            Toast.LENGTH_LONG
        ).show()
    }
}


fun openTelegramToUser(context: Context, username: String) {
    val telegramUri = Uri.parse("https://t.me/$username")
    val telegramIntent = Intent(Intent.ACTION_VIEW, telegramUri)

    // Check if the Telegram app is installed
    val packageManager = context.packageManager
    if (telegramIntent.resolveActivity(packageManager) != null) {
        // Telegram app is installed, open it
        context.startActivity(telegramIntent)
    } else {
        // Telegram app is not installed, open in web browser
        telegramIntent.data = telegramUri
        context.startActivity(telegramIntent)
    }
}