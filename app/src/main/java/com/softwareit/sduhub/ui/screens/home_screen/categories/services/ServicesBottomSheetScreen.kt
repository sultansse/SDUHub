package com.softwareit.sduhub.ui.screens.home_screen.categories.services

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
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
import com.softwareit.sduhub.ui.screens.home_screen.categories.services.faculties_screen.FacultiesScreenClass
import com.softwareit.sduhub.ui.screens.home_screen.categories.services.sdu_library_screen.SduLibraryScreenClass
import com.softwareit.sduhub.ui.screens.home_screen.categories.services.student_clubs_screen.StudentClubsScreenClass
import com.softwareit.sduhub.ui.screens.home_screen.components.CategoryDIO
import com.softwareit.sduhub.utils.common.openTelegramToUser
import kotlinx.parcelize.Parcelize
import okhttp3.internal.immutableListOf


val services = immutableListOf(
    CategoryDIO(
        icon = R.drawable.img_library,
        title = "SDU Library" // todo stringres
    ),
    CategoryDIO(
        icon = R.drawable.img_library,
        title = "Lost & Found" // todo stringres
    ),
    CategoryDIO(
        icon = R.drawable.img_library,
        title = "Faculties" // todo stringres
    ),
    CategoryDIO(
        icon = R.drawable.img_library,
        title = "Student Clubs" // todo stringres
    ),
)


@Parcelize
class ServicesBottomSheetScreen(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {

    @[Composable ExperimentalMaterial3Api]
    override fun Content() {
        val parent = LocalContainerScreen.current
        val parentScreen = parent as StackScreen

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Services") },
                    navigationIcon = {
                        IconButton(onClick = { parentScreen.back() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) {
            Box(modifier = Modifier.padding(it)) {
                ServicesScreen()
            }
        }
    }
    @Composable
    fun ServicesScreen() {
        val context = LocalContext.current
        val parent = LocalContainerScreen.current
        val parentScreen = parent as StackScreen

        LazyColumn {
            items(services.size, key = { services[it].title }) { index ->
                ServiceItem(
                    icon = services[index].icon,
                    title = services[index].title,
                    onClick = {
                        navigateToService(services[index].title, context, parentScreen)
                    }
                )
            }
        }
    }

    @Composable
    fun ServiceItem(
        icon: Int,
        title: String,
        onClick: () -> Unit,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable { onClick() }
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Image(
                painter = rememberAsyncImagePainter(icon),
                contentDescription = "icon of service",
                modifier = Modifier.size(56.dp)
                    .clip(CircleShape)
            )
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp),
            )
        }
    }
}


fun navigateToService(
    title: String,
    context: Context,
    navigator: NavigationContainer<StackState>
) {
// todo refactor
    val serviceActions = mapOf(
        services[0].title to { navigator.forward(SduLibraryScreenClass()) },
        services[1].title to { openTelegramToUser(context, "SDU_Lost_AND_Found") },
        services[2].title to { navigator.forward(FacultiesScreenClass()) },
        services[3].title to { navigator.forward(StudentClubsScreenClass()) },
    )

    serviceActions[title]?.invoke()
}