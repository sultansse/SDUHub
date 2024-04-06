package com.softwareit.sduhub.ui.screens.resources_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.softwareit.sduhub.R
import com.softwareit.sduhub.ui.screens.resources_screen.InternshipItemDTO
import com.softwareit.sduhub.ui.theme.colorSduDarkGray
import com.softwareit.sduhub.ui.theme.colorSduLightGray
import com.softwareit.sduhub.utils.datastore.DataStoreUtil
import okhttp3.internal.immutableListOf
import org.koin.compose.koinInject

val iconList = immutableListOf(
    R.drawable.img_abstract_rocket,
    R.drawable.img_abstract_bank,
    R.drawable.img_abstract_flower,
    R.drawable.img_abstract_ice,
    R.drawable.img_abstract_light,
)

@Composable
internal fun InternshipsItem(
    internship: InternshipItemDTO,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dataStoreUtil: DataStoreUtil = koinInject()
    val isDarkThemeEnabled by dataStoreUtil.getTheme()
        .collectAsState(initial = isSystemInDarkTheme())

    val boxBackgroundColor by remember(isDarkThemeEnabled) {
        if (isDarkThemeEnabled) mutableStateOf(colorSduDarkGray)
        else mutableStateOf(colorSduLightGray)
    }

    val boxBorderColor by remember(isDarkThemeEnabled) {
        if (isDarkThemeEnabled) mutableStateOf(colorSduLightGray)
        else mutableStateOf(colorSduDarkGray)
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(boxBackgroundColor, RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = boxBorderColor,
                shape = RoundedCornerShape(8),
            )
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()

            ) {

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
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Text(
                        text = internship.placeFormat,
                        fontFamily = FontFamily(Font(R.font.amiko_regular)),
                        modifier = Modifier
                            .border(
                                width = 0.5.dp,
                                color = boxBorderColor,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(4.dp)
                    )
                    Text(
                        text = internship.timeFormat,
                        fontFamily = FontFamily(Font(R.font.amiko_regular)),
                        modifier = Modifier
                            .border(
                                width = 0.5.dp,
                                color = boxBorderColor,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(4.dp)
                    )
                }

                Text(
                    text = internship.salary,
                    textAlign = TextAlign.End,
                    maxLines = 1,
                    fontFamily = FontFamily(Font(R.font.amiko_bold)),
                )
            }
        }
    }

}
