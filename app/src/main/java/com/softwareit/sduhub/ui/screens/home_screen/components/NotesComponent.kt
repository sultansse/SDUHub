package com.softwareit.sduhub.ui.screens.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.softwareit.sduhub.R
import com.softwareit.sduhub.data.local.datastore.DataStoreUtil
import com.softwareit.sduhub.data.local.room.notes.NoteDBO
import com.softwareit.sduhub.ui.screens.home_screen.HomeScreenContract
import org.koin.compose.koinInject


@Composable
fun NoteItem(
    note: NoteDBO,
    onUiEvent: (HomeScreenContract.Event) -> Unit,
    modifier: Modifier = Modifier,
) {
    val dataStoreUtil: DataStoreUtil = koinInject()
    val isDarkThemeEnabled by dataStoreUtil.getTheme()
        .collectAsState(initial = isSystemInDarkTheme())

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {

        val imageDarkerFilter = remember(isDarkThemeEnabled) {
            if (isDarkThemeEnabled) {
                ColorFilter.tint(
                    color = Color.Black.copy(alpha = 0.5f),
                    blendMode = BlendMode.Darken
                )
            } else {
                null
            }
        }
        Image(
            painter = rememberAsyncImagePainter(R.drawable.img_bg_note),
            contentDescription = "background image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize(),
            colorFilter = imageDarkerFilter,
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = note.title,
                    fontFamily = FontFamily(Font(R.font.amiko_semi_bold)),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 24.sp,
                    modifier = Modifier.weight(1f)
                )

                var isMenuExpanded by remember { mutableStateOf(false) }
                IconButton(
                    onClick = { isMenuExpanded = isMenuExpanded.not() }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More options"
                    )
                    DropdownMenu(
                        expanded = isMenuExpanded,
                        onDismissRequest = {
                            isMenuExpanded = false
                        },
                        content = {
                            DropdownMenuItem(
                                onClick = {
                                    isMenuExpanded = false
                                    onUiEvent(HomeScreenContract.Event.OnNoteDeleted(note.id))
                                },
                                text = {
                                    Text(stringResource(R.string.delete))
                                }
                            )
                            DropdownMenuItem(
                                onClick = {
                                    isMenuExpanded = false
                                    onUiEvent(HomeScreenContract.Event.OnNoteCopied(note))
                                },
                                text = {
                                    Text(stringResource(R.string.copy))
                                }
                            )
                        }
                    )
                }
            }

            Text(
                text = note.description,
                fontFamily = FontFamily(Font(R.font.amiko_regular)),
                maxLines = 3,
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                fontFamily = FontFamily(Font(R.font.amiko_semi_bold)),
                text = note.updatedAt,
            )
        }
    }
}