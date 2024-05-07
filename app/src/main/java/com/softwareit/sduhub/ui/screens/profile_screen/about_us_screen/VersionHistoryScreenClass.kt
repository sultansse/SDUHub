package com.softwareit.sduhub.ui.screens.profile_screen.about_us_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.terrakok.modo.LocalContainerScreen
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.StackScreen
import com.github.terrakok.modo.stack.back
import com.softwareit.sduhub.R
import kotlinx.parcelize.Parcelize
import okhttp3.internal.immutableListOf

@Parcelize
class VersionHistoryScreenClass(
    override val screenKey: ScreenKey = generateScreenKey(),
) : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val parent = LocalContainerScreen.current
        val parentScreen = parent as StackScreen
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.version_history)) },
                    navigationIcon = {
                        IconButton(onClick = { parentScreen.back() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            },
        ) {
            Box(modifier = Modifier.padding(it)) {
                VersionHistoryScreen()
            }
        }
    }

    @Composable
    private fun VersionHistoryScreen() {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(versions) {
                VersionHistoryItem(it)
            }
        }
    }

    @Composable
    private fun VersionHistoryItem(version: VersionDIO) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Column {
                Text(
                    text = version.versionName,
                    fontFamily = FontFamily(Font(R.font.amiko_semi_bold)),
                    fontSize = 16.sp
                )
                Text(
                    text = version.description,
                    fontFamily = FontFamily(Font(R.font.amiko_regular)),
                    fontSize = 14.sp
                )
                HorizontalDivider()
            }
        }
    }
}

// todo later move this into backend
private val versions = immutableListOf(
    VersionDIO(
        id = 0,
        versionName = "1.0.0-alpha03",
        description = """
            - Enhanced performance and stability
            - Introduced order bots
            - Added faculties feature
            - Included student clubs functionality
            - Implemented about us screen
            - Authentication now handled by backend
        """.trimIndent()
    ),
    VersionDIO(
        id = 1,
        versionName = "1.0.0-alpha02",
        description = """
            - Major refactoring:
                - Resolved crashes occurring in absence of internet connectivity
                - Integrated Lottie animations
                - Streamlined codebase
                - Improved UI responsiveness using stable Compose models
                - Corrected theme colors for screens like Internship details
                - FAQ now functional even without internet
        """.trimIndent()
    ),
    VersionDIO(
        id = 2,
        versionName = "1.0.0-alpha01",
        description = """
            - Initial release of SDUHub app MVP
            - Features smooth animations and optimal UX/UI design
            - Includes various functionalities such as:
                - Note-taking
                - Stories for staying updated
                - Widgets for quick navigation to services
                - AI chat bot
                - Delivery of urgent and important university information
                - Access to interesting podcasts, news, etc.
                - Listings of available internships
                - Latest news updates
                - Indoor navigation support
                - Dark/Light theme options
                - FAQ section
                - Numerous useful links to Telegram channels and groups
        """.trimIndent()
    ),
)

@Immutable
data class VersionDIO(
    val id: Long = 0,
    val versionName: String,
    val description: String,
)