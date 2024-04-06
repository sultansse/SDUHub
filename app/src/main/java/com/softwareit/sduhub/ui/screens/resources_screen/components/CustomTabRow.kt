package com.softwareit.sduhub.ui.screens.resources_screen.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.softwareit.sduhub.R
import com.softwareit.sduhub.ui.theme.colorSduBlue
import com.softwareit.sduhub.ui.theme.colorWhite
import com.softwareit.sduhub.utils.datastore.DataStoreUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import org.koin.compose.koinInject


@Composable
internal fun PagerToggle(
    selectedTabIndex: Int,
    onClick: (Int) -> Unit,
) {
    val dataStoreUtil: DataStoreUtil = koinInject()
    val isDarkThemeEnabled by dataStoreUtil.getTheme().collectAsState(initial = isSystemInDarkTheme())

    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier
            .padding(vertical = 14.dp, horizontal = 64.dp)
            .height(45.dp)
            .clip(
                shape = RoundedCornerShape(20.dp)
            ),
        divider = {},
        indicator = @Composable { tabPositions: List<TabPosition> ->
            MyNewIndicator(
                Modifier.myTabIndicatorOffset(tabPositions[selectedTabIndex])
            )
        },
    ) {
        ResourceTab.entries.forEach { tab ->
            Tab(
                text = {
                    Text(
                        text = tab.title,
                        fontFamily = FontFamily(Font(R.font.amiko_semi_bold)),
                    )
                },
                interactionSource = DisabledInteractionSource(),
                selected = tab.page == selectedTabIndex,
                selectedContentColor = colorWhite,
                unselectedContentColor = if (isDarkThemeEnabled) colorWhite.copy(alpha = 0.6f) else colorSduBlue,
                onClick = { onClick(tab.page) },
                modifier = Modifier
                    .zIndex(1f)
            )
        }
    }
}

@Composable
fun MyNewIndicator(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .padding(5.dp)
            .background(
                colorSduBlue,
                RoundedCornerShape(20.dp),
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
    }
}

enum class ResourceTab(val title: String, val page: Int) {
    INTERNSHIPS("Internships", 0),
    NEWS("News", 1)
}


class DisabledInteractionSource : MutableInteractionSource {
    override val interactions: Flow<Interaction> = emptyFlow()
    override suspend fun emit(interaction: Interaction) {}
    override fun tryEmit(interaction: Interaction) = true
}

// extension modifier for this specific tab indicator
private fun Modifier.myTabIndicatorOffset(
    currentTabPosition: TabPosition
): Modifier = composed() {
    val currentTabWidth by animateDpAsState(
        targetValue = currentTabPosition.width,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing),
        label = ""
    )
    val indicatorOffset by animateDpAsState(
        targetValue = currentTabPosition.left,
        animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing),
        label = ""
    )
    wrapContentSize(Alignment.CenterStart)
        .offset(x = indicatorOffset)
        .width(currentTabWidth)
}
