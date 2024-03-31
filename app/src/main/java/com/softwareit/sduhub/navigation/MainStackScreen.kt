package com.softwareit.sduhub.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.stack.StackNavModel
import com.github.terrakok.modo.stack.StackScreen
import com.softwareit.sduhub.ui.SlideTransition
import com.softwareit.sduhub.utils.common.presentation.hideKeyboardOnOutsideClick
import kotlinx.parcelize.Parcelize

/**
 * Root screen of the application.
 * It stores the main navigation multiscreen with each stack on it.
 * */
@Parcelize
class MainStackScreen(
    private val stackNavModel: StackNavModel
) : StackScreen(stackNavModel) {

    constructor(rootScreen: Screen) : this(StackNavModel(rootScreen))

    @Composable
    override fun Content() {
        Box(
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.systemBars)
                .hideKeyboardOnOutsideClick()
        ) {
            TopScreenContent {
                SlideTransition()
            }
        }
    }
}
