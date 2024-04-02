package com.softwareit.sduhub.ui.screens.resources_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.softwareit.sduhub.R
import com.softwareit.sduhub.ui.screens.resources_screen.InternshipItemDTO
import okhttp3.internal.immutableListOf


@Composable
internal fun InternshipsItem(internship: InternshipItemDTO, onClick: () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .border(1.dp, Color.Gray)
            .clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            val iconList = immutableListOf(
                R.drawable.img_abstract_rocket,
                R.drawable.img_abstract_bank,
                R.drawable.img_abstract_flower,
                R.drawable.img_abstract_ice,
                R.drawable.img_abstract_light,
            )

            Image(
                painter = rememberAsyncImagePainter(iconList.random()),
                contentDescription = internship.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(56.dp),
            )
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = internship.title,
                    fontFamily = FontFamily(Font(R.font.amiko_semi_bold)),
                    fontSize = 20.sp,
                )
                Text(
                    text = internship.company,
                    fontFamily = FontFamily(Font(R.font.amiko_regular)),
                    color = Color.Gray
                )
            }
        }
    }

}
