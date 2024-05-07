package com.softwareit.sduhub.ui.screens.profile_screen.about_us_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.terrakok.modo.LocalContainerScreen
import com.github.terrakok.modo.NavigationContainer
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.StackScreen
import com.github.terrakok.modo.stack.StackState
import com.github.terrakok.modo.stack.back
import com.github.terrakok.modo.stack.forward
import com.softwareit.sduhub.R
import com.softwareit.sduhub.utils.Constants.Companion.BASE_URL
import com.softwareit.sduhub.utils.common.getAppVersion
import com.softwareit.sduhub.utils.common.openWebsite
import kotlinx.parcelize.Parcelize

@Parcelize
class AboutUsScreenClass(
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
                    title = { Text(stringResource(R.string.about_us)) },
                    navigationIcon = {
                        IconButton(onClick = { parentScreen.back() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            },
            bottomBar = {
                AboutUsBottom()
            }
        ) {
            Box(modifier = Modifier.padding(it)) {
                AboutUsScreen(parentScreen)
            }
        }
    }

    @Composable
    fun AboutUsScreen(navigator: NavigationContainer<StackState>) {
        val context = LocalContext.current

        LazyColumn {
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_sduhub),
                        contentDescription = "About Us",
                        modifier = Modifier
                            .size(200.dp)
                            .padding(16.dp)
                    )
                    Text(
                        text = "SDUHub ${getAppVersion(context)}",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.amiko_regular)),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }

            // Политика конфиденциальности
            item {
                AboutUsItem(
                    title = stringResource(R.string.privacy_policy),
                    modifier = Modifier.clickable { openWebsite(context, "${BASE_URL}static/docs/privacy-policy.html") },
                )
            }

            // Лицензионное соглашение
            item {
                AboutUsItem(
                    title = stringResource(id = R.string.license_agreement),
                    modifier = Modifier.clickable { openWebsite(context, "${BASE_URL}static/docs/license-agreement.html") },
                )
            }

            // Условия использования
            item {
                AboutUsItem(
                    title = stringResource(id = R.string.terms_of_use),
                    modifier = Modifier.clickable { openWebsite(context, "${BASE_URL}static/docs/terms-of-use.html") },
                )
            }

            // История версии
            item {
                AboutUsItem(
                    title = stringResource(id = R.string.version_history),
                    modifier = Modifier.clickable { navigator.forward(VersionHistoryScreenClass()) },
                )
            }

            // О Разработчиках
            item {
                AboutUsItem(
                    title = stringResource(id = R.string.about_developers),
                    modifier = Modifier.clickable { navigator.forward(AboutDevelopersScreenClass()) },
                )
            }
        }

    }

    @Composable
    private fun AboutUsItem(title: String, modifier: Modifier = Modifier) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = title,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.amiko_regular)),
            )
            Icon(
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = "Arrow Right",
                modifier = Modifier.size(24.dp)
            )
        }
    }

    @Composable
    private fun AboutUsBottom() {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(
                text = stringResource(R.string._2024_sduhub_all_rights_reserved),
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                fontFamily = FontFamily(Font(R.font.amiko_semi_bold)),
            )
        }
    }
}