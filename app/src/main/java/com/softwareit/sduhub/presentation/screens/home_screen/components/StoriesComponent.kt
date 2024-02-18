package com.softwareit.sduhub.presentation.screens.home_screen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.appsamurai.storyly.StoryGroupSize
import com.appsamurai.storyly.StorylyInit
import com.appsamurai.storyly.StorylyView
import com.appsamurai.storyly.config.StorylyConfig
import com.appsamurai.storyly.config.styling.group.StorylyStoryGroupStyling
import com.softwareit.sduhub.presentation.utils.Constants

@Composable
fun Stories() {
    Row {
        AndroidView(
            factory = { context ->
                StorylyView(context).apply {
                    storylyInit = StorylyInit(
                        storylyId = Constants.STORYLY_INSTANCE_TOKEN,
                        config = StorylyConfig
                            .Builder()
                            .setStoryGroupStyling(
                                styling = StorylyStoryGroupStyling
                                    .Builder()
                                    .setTitleVisibility(isVisible = false)
                                    .setSize(
                                        size = StoryGroupSize.Custom
                                    )
                                    .setIconCornerRadius(60)
                                    .setIconHeight(600)
                                    .setIconWidth(540)
                                    .build()
                            )
                            .build()
                    )

                }
            }
        )
    }
}