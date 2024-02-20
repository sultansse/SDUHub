package com.softwareit.sduhub.ui.screens.home_screen.components

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
            icon = R.drawable.img_sdu,
            title = "MySDU"
        ),
        CategoryDto(
            icon = R.drawable.img_sdu,
            title = "sdu.kz"
        ),
        CategoryDto(
            icon = R.drawable.img_sdu,
            title = "Student clubs"
        ),
        CategoryDto(
            icon = R.drawable.img_sdu,
            title = "Free Offices"
        ),
        CategoryDto(
            icon = R.drawable.img_moodle,
            title = "Moodle"
        ),
        CategoryDto(
            icon = R.drawable.img_sdu,
            title = "Library"
        ),
    )

    VerticalGrid(
        columns = SimpleGridCells.Fixed(4),
        modifier = Modifier.padding(8.dp)
    ) {
        categories.forEach { category ->
            Category(category.icon, category.title)
        }
    }
}

@Composable
fun Category(
    icon: Int,
    title: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(horizontal = 2.dp, vertical = 8.dp)
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = title,
            modifier = Modifier
                .size(50.dp)
                .shadow(elevation = 4.dp, shape = CircleShape)
                .clip(CircleShape)
        )
        Text(
            text = title,
            textAlign = TextAlign.Center,
        )
    }
}
