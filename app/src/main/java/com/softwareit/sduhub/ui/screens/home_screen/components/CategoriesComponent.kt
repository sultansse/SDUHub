package com.softwareit.sduhub.ui.screens.home_screen.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.github.terrakok.modo.NavigationContainer
import com.github.terrakok.modo.stack.StackState
import com.github.terrakok.modo.stack.forward
import com.softwareit.sduhub.R
import com.softwareit.sduhub.ui.model.ElementDIO
import com.softwareit.sduhub.ui.screens.home_screen.HomeScreenContract
import com.softwareit.sduhub.ui.screens.home_screen.categories.ai_dos_screen.AiDosScreenClass
import com.softwareit.sduhub.ui.screens.home_screen.categories.moodle_screen.MoodleScreenClass
import com.softwareit.sduhub.ui.screens.home_screen.categories.my_sdu_screen.MySduScreenClass
import com.softwareit.sduhub.ui.screens.home_screen.categories.sdukz_screen.SduKzScreenClass
import com.softwareit.sduhub.ui.theme.colorWhite
import com.softwareit.sduhub.utils.common.openGmail
import com.softwareit.sduhub.utils.common.openTelegramToUser
import io.woong.compose.grid.SimpleGridCells
import io.woong.compose.grid.VerticalGrid
import okhttp3.internal.immutableListOf

val categories = immutableListOf(
    ElementDIO(
        id = 0,
        icon = R.drawable.img_aidos,
        title = "AI Dos"
    ),
    ElementDIO(
        id = 1,
        icon = R.drawable.img_gmail,
        title = "Gmail"
    ),
    ElementDIO(
        id = 2,
        icon = R.drawable.img_mysdu,
        title = "MySDU"
    ),
    ElementDIO(
        id = 3,
        icon = R.drawable.img_sdukz,
        title = "Sdu.kz"
    ),
    ElementDIO(
        id = 4,
        icon = R.drawable.img_order_food,
        title = "Order Food"
    ),
    ElementDIO(
        id = 5,
        icon = R.drawable.img_free_offices,
        title = "Free Offices"
    ),
    ElementDIO(
        id = 6,
        icon = R.drawable.img_moodle,
        title = "Moodle"
    ),
    ElementDIO(
        id = 7,
        icon = R.drawable.img_more_horz,
        title = "More"
    ),
)

@Composable
fun Categories(
    onUiEvent: (HomeScreenContract.Event) -> Unit,
    navigator: NavigationContainer<StackState>,
) {
    VerticalGrid(
        columns = SimpleGridCells.Fixed(4),
        modifier = Modifier.padding(8.dp)
    ) {
        repeat(categories.size) {
            CategoryItem(
                ElementDIO(
                    id = categories[it].id,
                icon = categories[it].icon,
                title = categories[it].title,
                ),
                onUiEvent = onUiEvent,
                navigator = navigator,
            )
        }
    }
}

@Composable
fun CategoryItem(
    category: ElementDIO,
    onUiEvent: (HomeScreenContract.Event) -> Unit,
    navigator: NavigationContainer<StackState>,
) {
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(horizontal = 2.dp, vertical = 8.dp)
            .clickable {
                navigateToCategory(category.id, context, navigator, onUiEvent)
            }
    ) {
        Image(
            painter = rememberAsyncImagePainter(category.icon),
            contentDescription = category.title,
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(colorWhite)
                .shadow(elevation = 4.dp, shape = CircleShape)
        )
        Text(
            text = category.title,
            fontFamily = FontFamily(Font(R.font.amiko_semi_bold)),
            textAlign = TextAlign.Center,
        )
    }
}

internal fun navigateToCategory(
    categoryId: Int,
    context: Context,
    navigator: NavigationContainer<StackState>,
    onUiEvent: (HomeScreenContract.Event) -> Unit,
) {
    when (categoryId) {
        0 -> navigator.forward(AiDosScreenClass())
        1 -> openGmail(context, "teacher@sdu.edu.kz", "Your subject", "Hello Teacher! I am [your name].. \n\n\n Regards, [your name]")
        2 -> navigator.forward(MySduScreenClass())
        3 -> navigator.forward(SduKzScreenClass())
        4 -> openTelegramToUser(context, "SDUOrder_bot")
        5 -> openTelegramToUser(context, "sduflexbot")
        6 -> navigator.forward(MoodleScreenClass())
        7 -> onUiEvent(HomeScreenContract.Event.OnServicesClicked)
    }
}