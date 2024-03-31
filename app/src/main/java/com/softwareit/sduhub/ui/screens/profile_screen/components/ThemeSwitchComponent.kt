package com.softwareit.sduhub.ui.screens.profile_screen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.softwareit.sduhub.R


@Composable
fun ThemeSwitchComponent() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            painter = rememberAsyncImagePainter(R.drawable.ic_dark_mode),
            contentDescription = "Dark mode",
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 32.dp)
        )
        Text(
            text = stringResource(R.string.dark_mode),
            modifier = Modifier.weight(1f)
        )
        Switch(
            checked = false,
            onCheckedChange = {
//          todo make change of theme to night theme
            },
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 32.dp)
        )
    }
}

