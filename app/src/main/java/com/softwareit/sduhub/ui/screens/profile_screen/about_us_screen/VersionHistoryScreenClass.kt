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
             - Works faster and better
             - added order bots
             - added faculties
             - added student clubs
             - added about us screen
             - auth goes to backend
        """.trimIndent()
    ),
    VersionDIO(
        id = 1,
        versionName = "1.0.0-alpha02",
        description = "Big refactoring:\n" +
                "- fixed crashes when internet was not available\n" +
                "- added lottie animations \n" +
                "- refactored code\n" +
                "- made ui faster via stable compose models\n" +
                "- fixed colors for theme for screens Internship details and other places\n" +
                "- faq works also via internet"
    ),
    VersionDIO(
        id = 2,
        versionName = "1.0.0-alpha01",
        description = "The MVP of app SDUHub\n" +
                "\n" +
                "It has smooth animations, fast and good UX/UI. The app includes several features, including: \n" +
                "- Note taking\n" +
                "- Stories to up to date\n" +
                "- Widgets for fast navigation to services\n" +
                "- AI chat bot\n" +
                "- Urgent and Important info from university\n" +
                "- Interesting and recommended podcasts, news etc\n" +
                "- Internships\n" +
                "- News\n" +
                "- Indoor Navigation\n" +
                "- Dark/Light theme\n" +
                "- Faq\n" +
                "- Many useful links to telegram channels and groups"
    ),
)

@Immutable
data class VersionDIO(
    val id: Long = 0,
    val versionName: String,
    val description: String,
)