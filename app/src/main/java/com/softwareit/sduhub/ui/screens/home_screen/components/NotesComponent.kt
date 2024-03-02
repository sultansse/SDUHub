package com.softwareit.sduhub.ui.screens.home_screen.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.softwareit.sduhub.R
import com.softwareit.sduhub.data.local.notes.NoteDTO
import com.softwareit.sduhub.ui.screens.home_screen.HomeScreenViewModel

@Composable
fun NotesComponent(notes: List<NoteDTO>) {
    val viewModel: HomeScreenViewModel = viewModel()
    Column {
        notes.forEach {
            NoteItem(
                note = it,
                onNoteClick = {
                    viewModel.goToEditNote(noteId = it.id)
                },
                onNoteMenuClick = {
                    // TODO open dropdown menu
                },
            )
        }
    }
}


@Composable
fun NoteItem(
    note: NoteDTO,
    onNoteClick: () -> Unit,
    onNoteMenuClick: () -> Unit,
) {

    val context = LocalContext.current
    val backgroundImage = rememberAsyncImagePainter(R.drawable.img_bg_note)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                Toast
                    .makeText(context, "Note ${note.id} is clicked", Toast.LENGTH_SHORT)
                    .show()
                onNoteClick()
            }
    ) {
        Image(
            painter = backgroundImage,
            contentDescription = "background image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        ConstraintLayout {
            val (content, moreIcon) = createRefs()

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .constrainAs(content) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
            ) {
                Text(
                    text = note.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                )
                Text(text = note.description)
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    text = note.created_at
                )
            }

            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More options",
                modifier = Modifier
                    .padding(16.dp)
                    .clip(CircleShape)
                    .clickable {
                        Toast
                            .makeText(context, "More is clicked", Toast.LENGTH_SHORT)
                            .show()
                        onNoteMenuClick()
                    }
                    .constrainAs(moreIcon) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
            )

        }
    }
}