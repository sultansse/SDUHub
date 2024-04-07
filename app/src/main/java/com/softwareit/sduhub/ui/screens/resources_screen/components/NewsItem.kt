package com.softwareit.sduhub.ui.screens.resources_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.softwareit.sduhub.R
import com.softwareit.sduhub.ui.model.NewsDIO


@Composable
internal fun NewsItem(news: NewsDIO, onClick: () -> Unit) {
    HorizontalDivider()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable { onClick() },
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(news.imageUrl)
                .setHeader("User-Agent", "Mozilla/5.0")
                .build(),
            placeholder = rememberAsyncImagePainter(R.drawable.img_sduhub),
            contentDescription = news.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(12.dp))
                .clipToBounds(),
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = news.title,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                fontFamily = FontFamily(Font(R.font.amiko_semi_bold)),
                fontSize = 18.sp,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = news.date,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                fontFamily = FontFamily(Font(R.font.amiko_regular)),
                fontSize = 14.sp,
            )
        }
    }

}