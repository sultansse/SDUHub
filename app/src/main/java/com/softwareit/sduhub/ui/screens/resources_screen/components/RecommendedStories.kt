package com.softwareit.sduhub.ui.screens.resources_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.softwareit.sduhub.R
import com.softwareit.sduhub.ui.screens.home_screen.components.StorylyViewComponent
import com.softwareit.sduhub.utils.Constants


@Composable
internal fun Recommended() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.recommended),
            fontFamily = FontFamily(Font(R.font.amiko_semi_bold)),
            fontSize = 28.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        StorylyViewComponent(
            Constants.STORYLY_TRENDS_TOKEN,
            groupIconHeight = 520,
            groupIconWidth = 460,
            groupIconCornerRadius = 2,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}