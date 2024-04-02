package com.softwareit.sduhub.utils.common

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.content.ContextCompat



/**
 * Opens an email client with pre-filled recipient, subject, and body.
 *
 * @param context The context from which the intent is started.
 * @param to The recipient email address.
 * @param subject The subject of the email.
 * @param body The body text of the email.
 */
fun openGmail(context: Context, to: String, subject: String, body: String) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:") // Only email apps should handle this
        putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, body)
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