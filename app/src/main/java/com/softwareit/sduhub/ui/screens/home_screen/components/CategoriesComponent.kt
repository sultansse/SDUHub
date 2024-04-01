package com.softwareit.sduhub.ui.screens.home_screen.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.github.terrakok.modo.LocalContainerScreen
import com.github.terrakok.modo.NavigationContainer
import com.github.terrakok.modo.stack.StackScreen
import com.github.terrakok.modo.stack.StackState
import com.github.terrakok.modo.stack.forward
import com.softwareit.sduhub.R
import com.softwareit.sduhub.ui.screens.home_screen.categories.ai_dos_screen.AiDosScreenClass
import com.softwareit.sduhub.ui.screens.home_screen.categories.moodle_screen.MoodleScreenClass
import com.softwareit.sduhub.ui.screens.home_screen.categories.my_sdu_screen.MySduScreenClass
import com.softwareit.sduhub.ui.screens.home_screen.categories.sdu_library_screen.SduLibraryScreenClass
import com.softwareit.sduhub.ui.screens.home_screen.categories.sdukz_screen.SduKzScreenClass
import com.softwareit.sduhub.utils.common.openGmail
import com.softwareit.sduhub.utils.common.openTelegramToUser
import io.woong.compose.grid.SimpleGridCells
import io.woong.compose.grid.VerticalGrid
import okhttp3.internal.immutableListOf


data class CategoryDIO(
    val icon: Int,
    val title: String
)

val categories = immutableListOf(
    CategoryDIO(
        icon = R.drawable.img_aidos,
        title = "AI Dos"
    ),
    CategoryDIO(
        icon = R.drawable.img_gmail,
        title = "Gmail"
    ),
    CategoryDIO(
        icon = R.drawable.img_mysdu,
        title = "MySDU"
    ),
    CategoryDIO(
        icon = R.drawable.img_sdukz,
        title = "Sdu.kz"
    ),
    CategoryDIO(
        icon = R.drawable.img_order_food,
        title = "Order Food"
    ),
    CategoryDIO(
        icon = R.drawable.img_free_offices,
        title = "Free Offices"
    ),
    CategoryDIO(
        icon = R.drawable.img_moodle,
        title = "Moodle"
    ),
    CategoryDIO(
        icon = R.drawable.img_library,
        title = "Library"
    ),
)

@Composable
fun Categories() {
    val context = LocalContext.current
    val parent = LocalContainerScreen.current
    val parentScreen = parent as StackScreen


    VerticalGrid(
        columns = SimpleGridCells.Fixed(4),
        modifier = Modifier.padding(8.dp)
    ) {
        repeat(categories.size) {
            CategoryItem(
                icon = categories[it].icon,
                title = categories[it].title,
                onClick = {
                    navigateToCategory(categories[it].title, context, parentScreen)
                }
            )
        }
    }
}

@Composable
fun CategoryItem(
    icon: Int,
    title: String,
    onClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(horizontal = 2.dp, vertical = 8.dp)
            .clickable { onClick() }
    ) {
        Image(
            painter = rememberAsyncImagePainter(icon),
            contentDescription = title,
            modifier = Modifier
                .size(56.dp)
                .shadow(elevation = 4.dp, shape = CircleShape)
                .clip(CircleShape)
        )
        Text(
            text = title,
            fontFamily = FontFamily(Font(R.font.amiko_semi_bold)),
            textAlign = TextAlign.Center,
        )
    }
}

fun navigateToCategory(
    title: String,
    context: Context,
    navigator: NavigationContainer<StackState>
) {
    val categoryActions = mapOf(
        categories[0].title to { navigator.forward(AiDosScreenClass()) },
        categories[1].title to { openGmail(context) },
        categories[2].title to { navigator.forward(MySduScreenClass()) },
        categories[3].title to { navigator.forward(SduKzScreenClass()) },
        categories[4].title to { openTelegramToUser(context, "SDUOrder_bot") },
        categories[5].title to { openTelegramToUser(context, "sduflexbot") },
        categories[6].title to { navigator.forward(MoodleScreenClass()) },
        categories[7].title to { navigator.forward(SduLibraryScreenClass()) }
    )

    categoryActions[title]?.invoke()
}