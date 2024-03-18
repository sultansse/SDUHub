package com.softwareit.sduhub.ui.screens.home_screen.components

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.appsamurai.storyly.StoryGroupSize
import com.appsamurai.storyly.StorylyInit
import com.appsamurai.storyly.StorylyView
import com.appsamurai.storyly.config.StorylyConfig
import com.appsamurai.storyly.config.styling.group.StorylyStoryGroupStyling
import com.softwareit.sduhub.utils.Constants.Companion.STORYLY_INSTANCE_TOKEN

@Composable
fun Stories() {
    LazyRow {
        item {
            StorylyViewComponent(
                STORYLY_INSTANCE_TOKEN
            )
        }
    }
}

@Composable
fun StorylyViewComponent(
    token: String,
    groupIconHeight: Int = 420,
    groupIconWidth: Int = 380,
    groupIconCornerRadius: Int = 60,
) {
    AndroidView(
        factory = { context ->
            StorylyView(context).apply {

                val storyGroupStylyng = StorylyStoryGroupStyling.Builder()
                    .setTitleVisibility(isVisible = false)
                    .setSize(StoryGroupSize.Custom)
                    .setIconHeight(groupIconHeight)
                    .setIconWidth(groupIconWidth)
                    .setIconCornerRadius(groupIconCornerRadius)
                    .build()

                storylyInit = StorylyInit(
                    storylyId = token,
                    config = StorylyConfig.Builder()
                        .setStoryGroupStyling(storyGroupStylyng)
                        .build()
                )

            }
        }
    )
}
