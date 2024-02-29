package com.softwareit.sduhub.ui.screens.home_screen.components

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.appsamurai.storyly.StoryGroupSize
import com.appsamurai.storyly.StorylyInit
import com.appsamurai.storyly.StorylyView
import com.appsamurai.storyly.config.StorylyConfig
import com.appsamurai.storyly.config.styling.group.StorylyStoryGroupStyling
import com.softwareit.sduhub.utils.Constants

@Composable
fun Stories() {
    LazyRow {
        item {
            StorylyViewComponent()
        }
    }
}

@Composable
fun StorylyViewComponent() {
    AndroidView(
        factory = { context ->
            StorylyView(context).apply {

                val storyGroupStylyng = StorylyStoryGroupStyling.Builder()
                    .setTitleVisibility(isVisible = false)
                    .setSize(StoryGroupSize.Custom)
                    .setIconHeight(420)
                    .setIconWidth(380)
                    .setIconCornerRadius(60)
                    .build()

                storylyInit = StorylyInit(
                    storylyId = Constants.STORYLY_INSTANCE_TOKEN,
                    config = StorylyConfig.Builder()
                        .setStoryGroupStyling(storyGroupStylyng)
                        .build()
                )

            }
        }
    )
}
