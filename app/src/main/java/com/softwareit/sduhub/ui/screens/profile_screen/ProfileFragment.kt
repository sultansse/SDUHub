package com.softwareit.sduhub.ui.screens.profile_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.softwareit.sduhub.R
import com.softwareit.sduhub.ui.base.BaseFragment
import com.softwareit.sduhub.ui.screens.profile_screen.components.LogoutComponent
import com.softwareit.sduhub.ui.screens.profile_screen.components.ProfileHeaderComponent
import com.softwareit.sduhub.ui.screens.profile_screen.components.ProfileScreenListItemComponent
import com.softwareit.sduhub.ui.screens.profile_screen.components.ThemeSwitchComponent

class ProfileFragment : BaseFragment() {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun SetContent() {
        Scaffold(topBar = {
            TopAppBar(title = { Text(text = stringResource(R.string.app_name)) })
        }) {
            Box(modifier = Modifier.padding(it)) {
                ProfileScreen()
            }
        }
    }

    @Composable
    fun ProfileScreen() {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            item {
                ProfileHeaderComponent(
                    onClick = {
                        // todo open profile id card
                    }
                )
            }

            item {
                ThemeSwitchComponent()
            }

            items(5) {
                ProfileScreenListItemComponent(
                    onClick = {
                        // todo
                    }
                )
            }

            item {
                LogoutComponent()
            }
        }
    }

    @Preview(showSystemUi = true, showBackground = true)
    @Composable
    fun ProfileScreenPreview() {
        ProfileScreen()
    }
}
