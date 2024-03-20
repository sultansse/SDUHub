package com.softwareit.sduhub.ui.screens.profile_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.softwareit.sduhub.R
import com.softwareit.sduhub.core.BaseFragment
import com.softwareit.sduhub.ui.screens.profile_screen.components.ProfileHeaderComponent
import com.softwareit.sduhub.ui.screens.profile_screen.components.ProfileIdCardDialog
import com.softwareit.sduhub.ui.screens.profile_screen.components.ProfileScreenListItemComponent
import com.softwareit.sduhub.ui.screens.profile_screen.components.ThemeSwitchComponent
import okhttp3.internal.immutableListOf
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment() {

    private val viewModel: ProfileViewModel by viewModel()

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun SetContent() {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = stringResource(R.string.app_name)) })
            }
        ) {
            Box(modifier = Modifier.padding(it)) {
                ProfileScreen()
            }
        }
    }

    @Composable
    fun ProfileScreen() {

        val items = immutableListOf<ProfileScreenListItem>(
            ProfileScreenListItem(
                icon = R.drawable.ic_faq,
                title = "FAQ",
            ),
            ProfileScreenListItem(
                icon = R.drawable.ic_community,
                title = "Community",
            ),
            ProfileScreenListItem(
                icon = R.drawable.ic_language,
                title = "Change language",
            ),
            ProfileScreenListItem(
                icon = R.drawable.ic_favorite,
                title = "Favourite",
            ),
            ProfileScreenListItem(
                icon = R.drawable.ic_feedback,
                title = "Feedback",
            ),
            ProfileScreenListItem(
                icon = R.drawable.ic_logout,
                title = "Logout",
            ),
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            item {
                var dialogOpen by remember { mutableStateOf(false) }
                val student by viewModel.student.collectAsState()

                ProfileHeaderComponent(
                    student,
                    onClick = {
                        dialogOpen = true
                    }
                )

                if (dialogOpen) {
                    ProfileIdCardDialog(
                        student = student,
                        onClose = { dialogOpen = false }
                    )
                }
            }

            item {
                ThemeSwitchComponent()
            }

            items(items.size) { index ->
                ProfileScreenListItemComponent(
                    title = items[index].title,
                    icon = items[index].icon,
                    onClick = {
                        Toast.makeText(
                            requireContext(),
                            "Clicked on ${items[index].title}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            }
        }
    }

    @Preview(showSystemUi = true, showBackground = true)
    @Composable
    fun ProfileScreenPreview() {
        SetContent()
    }
}

data class ProfileScreenListItem(
    val icon: Int,
    val title: String,
)