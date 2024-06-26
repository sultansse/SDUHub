package com.softwareit.sduhub.ui.screens.home_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.softwareit.sduhub.R
import com.softwareit.sduhub.ui.model.ImportantInfoDIO


@Composable
fun ImportantInfo(data: ImportantInfoDIO) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8))
            .padding(16.dp)
            .border(
                width = 2.dp,
                color = Color.Red,
                shape = RoundedCornerShape(8),
            )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = data.title,
                fontFamily = FontFamily(Font(R.font.amiko_bold)),
                fontSize = 24.sp,
            )
            Text(
                text = data.description,
                fontFamily = FontFamily(Font(R.font.amiko_regular)),
                modifier = Modifier.padding(top = 16.dp)
            )
            if (data.tags.isNotEmpty()) {
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                ) {
                    data.tags.forEach {
                        Text(
                            text = it,
                            fontFamily = FontFamily(Font(R.font.amiko_semi_bold)),
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .border(BorderStroke(1.dp, Color.Red), RoundedCornerShape(20))
                                .padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImportantInfoComponentPreview() {
    ImportantInfo(
        ImportantInfoDIO(
            title = "Some title",
            description = "Lorem ipsum some description text",
            tags = listOf("13:00", "RedHall", "Podcast")
        )
    )
}