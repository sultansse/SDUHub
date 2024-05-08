package com.softwareit.sduhub.ui.screens.about_us_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.terrakok.modo.LocalContainerScreen
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.StackScreen
import com.github.terrakok.modo.stack.back
import com.softwareit.sduhub.R
import com.softwareit.sduhub.common.presentation.openGmail
import com.softwareit.sduhub.common.presentation.openWebsite
import com.softwareit.sduhub.data.local.datastore.DataStoreUtil
import com.softwareit.sduhub.ui.model.DeveloperDIO
import com.softwareit.sduhub.ui.theme.colorSduBlue
import com.softwareit.sduhub.ui.theme.colorSduLightBlue
import kotlinx.parcelize.Parcelize
import okhttp3.internal.immutableListOf
import org.koin.compose.koinInject

@Parcelize
class AboutDevelopersScreenClass(
    override val screenKey: ScreenKey = generateScreenKey(),
) : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val parent = LocalContainerScreen.current
        val parentScreen = parent as StackScreen
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(stringResource(R.string.about_developers)) },
                    navigationIcon = {
                        IconButton(onClick = { parentScreen.back() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    })
            },
        ) {
            Box(modifier = Modifier.padding(it)) {
                AboutDevelopersScreen()
            }
        }
    }

    @Composable
    private fun AboutDevelopersScreen() {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            items(developers.size, key = { developers[it].id }) { index ->
                DeveloperItem(developers[index])
            }
        }
    }

    @Composable
    private fun DeveloperItem(developer: DeveloperDIO) {
        val context = LocalContext.current
        var isExpanded by remember { mutableStateOf(false) }
        val rotationAngle by animateFloatAsState(
            targetValue = if (isExpanded) 180f else 0f,
            label = if (isExpanded) "Collapse" else "Expand"
        )
        val dataStoreUtil: DataStoreUtil = koinInject()
        val isDarkThemeEnabled by dataStoreUtil.getTheme()
            .collectAsState(initial = isSystemInDarkTheme())

        val linkColor by remember(isDarkThemeEnabled) {
            if (isDarkThemeEnabled) mutableStateOf(colorSduLightBlue)
            else mutableStateOf(colorSduBlue)
        }

        Column {
            Box(modifier = Modifier
                .fillMaxWidth()
                .clickable { isExpanded = isExpanded.not() }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = developer.fullname,
                            fontFamily = FontFamily(Font(R.font.amiko_semi_bold)),
                            fontSize = 20.sp,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Text(
                            text = developer.position,
                            fontFamily = FontFamily(Font(R.font.amiko_regular)),
                            fontSize = 16.sp,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = if (isExpanded) "Collapse" else "Expand",
                        modifier = Modifier.rotate(rotationAngle),
                    )
                }
            }
            // Only show this if isExpanded is true
            AnimatedVisibility(visible = isExpanded) {
                Column {
                    Text(
                        text = developer.description,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.amiko_regular)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                    if (developer.linkedin != null) {
                        Text(
                            text = "LinkedIn: ${developer.linkedin}",
                            fontSize = 16.sp,
                            color = linkColor,
                            fontFamily = FontFamily(Font(R.font.amiko_regular)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { openWebsite(context, developer.linkedin) }
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                    if (developer.email != null) {
                        Text(
                            text = "Email: ${developer.email}",
                            fontSize = 16.sp,
                            color = linkColor,
                            fontFamily = FontFamily(Font(R.font.amiko_regular)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { openGmail(context, developer.email) }
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                }
            }
            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
        }
    }
}

private val developers = immutableListOf(
    DeveloperDIO(
        id = 0,
        fullname = "Sultan Suleimenov",
        position = "Founder, Software Developer",
        linkedin = "https://www.linkedin.com/in/ssesultan/",
        email = "ss.softwareit@gmail.com",
        description = "I am a software developer with a passion for creating software that is both useful and enjoyable to use. I have experience in developing web applications, mobile applications, and Backend services. I am always looking for new challenges and opportunities to learn and grow as a developer.",
    ),
    DeveloperDIO(
        id = 1,
        fullname = "Ulan Satybaldin",
        position = "CEO",
        email = "200107077@stu.sdu.edu.kz",
        description = "I am a CEO of SoftwareIT company. I am responsible for the overall success of the company. I am a visionary leader who is able to see the big picture and plan for the future. I am also a strategic thinker who is able to make decisions that will benefit the company in the long run. I am passionate about technology and innovation, and I am always looking for ways to improve the company and its products.",
    ),
    DeveloperDIO(
        id = 2,
        fullname = "Bexultan Nurakyn",
        position = "Founder, Project Manager, Developer",
        linkedin = "https://www.linkedin.com/in/bexultan-nurakyn-804737237/",
        email = "200107071@stu.sdu.edu.kz",
        description = "I am a project manager and software developer with a passion for creating innovative software solutions. I have experience in managing software development projects from start to finish, and I am skilled in a variety of programming languages and technologies. I am always looking for new challenges and opportunities to learn and grow as a developer.",
    ),
)
