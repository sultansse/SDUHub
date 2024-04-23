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
import com.github.terrakok.modo.NavigationContainer
import com.github.terrakok.modo.stack.StackState
import com.softwareit.sduhub.ui.model.ElementDIO
import com.softwareit.sduhub.ui.screens.home_screen.navigateToService

@Composable
fun ServiceItem(
    service: ElementDIO,
    navigator: NavigationContainer<StackState>,
) {
    val context = LocalContext.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { navigateToService(service.id, context, navigator) }
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Image(
            painter = rememberAsyncImagePainter(service.icon),
            contentDescription = "icon of service",
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
        )
        Text(
            text = service.title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp),
        )
    }
}