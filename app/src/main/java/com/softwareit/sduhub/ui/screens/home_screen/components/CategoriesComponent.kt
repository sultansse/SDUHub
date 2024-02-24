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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.softwareit.sduhub.R
import io.woong.compose.grid.SimpleGridCells
import io.woong.compose.grid.VerticalGrid


data class CategoryDto(
    val icon: Int,
    val title: String
)

@Composable
fun Categories() {
    val categories = listOf(
        CategoryDto(
            icon = R.drawable.img_ai,
            title = "Ai assistant"
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
            title = "Sdu.kz"
        ),
        CategoryDto(
            icon = R.drawable.img_mysdu,
            title = "Student clubs"
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
                onCategoryClick = { navigateToCategory(context, category.title) },
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
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(horizontal = 2.dp, vertical = 8.dp)
            .clickable { onCategoryClick() }
    ) {
        Image(
            painter = painterResource(icon),
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

fun navigateToCategory(context: Context, title: String) {
    when (title) {
        "Gmail" -> {
            openGmail(context)
        }
        "Sdu.kz" -> {
            openMySdu(context)
        }
    }
}

fun openMySdu(context: Context) {
//    todo navigate to sdu.kz fragment
}

private fun openGmail(context: Context) {

    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf("recipient@example.com"))
        putExtra(Intent.EXTRA_SUBJECT, "Subject")
        putExtra(Intent.EXTRA_TEXT, "Body")
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