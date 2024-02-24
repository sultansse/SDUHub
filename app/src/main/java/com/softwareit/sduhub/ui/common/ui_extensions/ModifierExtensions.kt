package com.softwareit.sduhub.ui.common.ui_extensions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController

@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.hideKeyboardOnOutsideClick(): Modifier = composed {
    val controller = LocalSoftwareKeyboardController.current
    val focus = LocalFocusManager.current

    this then Modifier.noRippleClickable {
        controller?.hide()
        focus.clearFocus()
    }

}

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    this then Modifier.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
        onClick = onClick
    )
}