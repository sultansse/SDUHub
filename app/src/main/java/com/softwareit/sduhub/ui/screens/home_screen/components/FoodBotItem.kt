package com.softwareit.sduhub.ui.screens.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.softwareit.sduhub.ui.model.ElementDIO
import com.softwareit.sduhub.ui.screens.home_screen.navigateToOrderFood

@Composable
fun FoodBotItem(
    bot: ElementDIO,
) {
    val context = LocalContext.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { navigateToOrderFood(bot.id, context) }
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Image(
            painter = rememberAsyncImagePainter(bot.icon),
            contentDescription = "icon of service",
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
        )
        Text(
            text = bot.title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp),
        )
    }
}