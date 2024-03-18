package com.softwareit.sduhub.ui.screens.profile_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter


@Composable
fun ProfileScreenListItemComponent(
    icon: Int,
    title: String,
    onClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Icon(
            painter = rememberAsyncImagePainter(icon),
            contentDescription = "Logout",
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 32.dp)

        )
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .weight(1f)
        )
        Icon(
            imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
            contentDescription = "Open this block",
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 32.dp)        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenListItemComponentPreview() {
    ProfileScreenListItemComponent(
        icon = 0,
        title = "Some element in list",
        onClick = {}
    )
}