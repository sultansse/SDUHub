package com.softwareit.sduhub.ui.screens.profile_screen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.softwareit.sduhub.R
import com.softwareit.sduhub.utils.datastore.DataStoreUtil
import com.softwareit.sduhub.utils.datastore.ThemeViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject


@Composable
fun ThemeSwitchComponent() {
    val themeViewModel: ThemeViewModel = koinViewModel()
    val dataStoreUtil: DataStoreUtil = koinInject()

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
        var switchState by remember { themeViewModel.isDarkThemeEnabled }
        val coroutineScope = rememberCoroutineScope()


        Switch(
            checked = switchState,
            onCheckedChange = {
                switchState = it

                coroutineScope.launch {
                    dataStoreUtil.saveTheme(it)
                }
            },
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 32.dp)
        )
    }

}

