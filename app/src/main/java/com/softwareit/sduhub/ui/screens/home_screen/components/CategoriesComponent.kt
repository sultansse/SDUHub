package com.softwareit.sduhub.ui.screens.home_screen.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.rememberAsyncImagePainter
import com.softwareit.sduhub.R
import com.softwareit.sduhub.ui.screens.home_screen.HomeScreenViewModel
import io.woong.compose.grid.SimpleGridCells
import io.woong.compose.grid.VerticalGrid
import okhttp3.internal.immutableListOf


data class CategoryDto(
    val icon: Int,
    val title: String
)
// TODO do not pass viewmodel to composable
@Composable
fun Categories(viewModel: HomeScreenViewModel) {
    val categories = immutableListOf(
        CategoryDto(
            icon = R.drawable.img_aidos,
            title = "AI Dos"
        ),
        CategoryDto(
            icon = R.drawable.img_gmail,
            title = "Gmail"
        ),
        CategoryDto(
            icon = R.drawable.img_mysdu,
            title = "MySDU"
        ),
        CategoryDto(
            icon = R.drawable.img_sdukz,
            title = "sdu.kz"
        ),
        CategoryDto(
            icon = R.drawable.img_order_food,
            title = "Order Food"
        ),
        CategoryDto(
            icon = R.drawable.img_free_offices,
            title = "Free Offices"
        ),
        CategoryDto(
            icon = R.drawable.img_moodle,
            title = "Moodle"
        ),
        CategoryDto(
            icon = R.drawable.img_library,
            title = "Library"
        ),
    )

    VerticalGrid(
        columns = SimpleGridCells.Fixed(4),
        modifier = Modifier.padding(8.dp)
    ) {
        categories.forEach { category ->
            val context = LocalContext.current
            Category(
                category.icon,
                category.title,
                onCategoryClick = { navigateToCategory(context, category.title, viewModel) },
            )
        }
    }
}

@Composable
fun Category(
    icon: Int,
    title: String,
    onCategoryClick: () -> Unit,
) {

    val categoryIcon = rememberAsyncImagePainter(icon)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(horizontal = 2.dp, vertical = 8.dp)
            .clickable { onCategoryClick() }
    ) {
        Image(
            painter = categoryIcon,
            contentDescription = title,
            modifier = Modifier
                .size(56.dp)
                .shadow(elevation = 4.dp, shape = CircleShape)
                .clip(CircleShape)
        )
        Text(
            text = title,
            textAlign = TextAlign.Center,
        )
    }
}

fun navigateToCategory(context: Context, title: String, viewModel: HomeScreenViewModel) {
    when (title) {
        "Gmail" -> { openGmail(context) }

        "MySDU" -> { viewModel.goToMySdu() }

        "Sdu.kz" -> { viewModel.goToSduKz() }

        "Ai assistant" -> { viewModel.goToAiAssistant() }

        "Order Food" -> { openTelegramToUser("SDUOrder_bot", context) }

        "Free Offices" -> { openTelegramToUser("sduflexbot", context) }

        "Moodle" -> { viewModel.goToMoodle() }

        "Library" -> { viewModel.goToSduLibrary() }
    }
}


private fun openGmail(context: Context) {

    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf("recipient@stu.sdu.edu.kz"))
        putExtra(Intent.EXTRA_SUBJECT, "Subject")
        putExtra(Intent.EXTRA_TEXT, "Hello Teacher, I ......")
    }

    try {
        startActivity(context, intent, null)
    } catch (e: Exception) {
        e.printStackTrace()
        Toast.makeText(
            context,
            "not found any email client to open the email",
            Toast.LENGTH_LONG
        ).show()
    }
}


fun openTelegramToUser(username: String, context: Context) {
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