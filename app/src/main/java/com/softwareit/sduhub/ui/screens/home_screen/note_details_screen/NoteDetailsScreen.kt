package com.softwareit.sduhub.ui.screens.home_screen.note_details_screen

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.app.ShareCompat
import coil.compose.rememberAsyncImagePainter
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.github.terrakok.modo.LocalContainerScreen
import com.github.terrakok.modo.NavigationContainer
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.StackScreen
import com.github.terrakok.modo.stack.StackState
import com.github.terrakok.modo.stack.back
import com.softwareit.sduhub.R
import com.softwareit.sduhub.data.local.room.notes.NoteDBO
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel

@Parcelize
class NoteDetailsScreenClass(
    private val noteId: Int,
    override val screenKey: ScreenKey = generateScreenKey(),
) : Screen {

    @Composable
    override fun Content() {
        val parent = LocalContainerScreen.current
        val parentScreen = parent as StackScreen

        val viewModel: NoteDetailsViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()

        LaunchedEffect(key1 = true) {
            viewModel.setEvent(NoteDetailsContract.Event.OnFetchNote(noteId))
        }

        when (val state = uiState.noteState) {
            is NoteDetailsContract.NoteState.NoteFound -> {
                val composition by rememberLottieComposition(
                    LottieCompositionSpec.RawRes(R.raw.anim_not_found)
                )
                LottieAnimation(
                    composition = composition,
                    iterations = Int.MAX_VALUE,
                    alignment = Alignment.Center
                )
            }

            is NoteDetailsContract.NoteState.Fetched -> {
                Scaffold(
                    topBar = { EditNoteTopAppBar(parentScreen) },
                    bottomBar = { EditNoteBottomBar(state.note, parentScreen) },
                    content = {
                        Box(modifier = Modifier.padding(it)) {
                            EditNoteScreen(state.note)
                        }
                    }
                )
            }
        }
    }

    @Composable
    fun EditNoteScreen(note: NoteDBO) {

        val viewModel: NoteDetailsViewModel = koinViewModel()

        var title by remember { mutableStateOf(note.title) }
        var description by remember { mutableStateOf(note.description) }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TextField(
                value = title,
                onValueChange = {
                    title = it
                    viewModel.setEvent(NoteDetailsContract.Event.OnTitleChanged(it))
                },
                placeholder = {
                    Text(
                        text = "Title",
                        fontFamily = FontFamily(Font(R.font.amiko_regular)),
                    )
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    imeAction = ImeAction.Next,
                ),
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = description,
                onValueChange = {
                    description = it
                    viewModel.setEvent(NoteDetailsContract.Event.OnDescriptionChanged(it))
                },
                placeholder = {
                    Text(
                        text = "Description",
                        fontFamily = FontFamily(Font(R.font.amiko_regular)),
                    )
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    imeAction = ImeAction.Default,
                ),
                modifier = Modifier.fillMaxSize()
            )
        }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun EditNoteTopAppBar(
        navigator: NavigationContainer<StackState>,
    ) {
        val context = LocalContext.current
        val viewModel: NoteDetailsViewModel = koinViewModel()

        TopAppBar(
            navigationIcon = {
                IconButton(
                    onClick = { navigator.back() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                        contentDescription = "Go back"
                    )
                }
            },
            title = {
                Text(
                    text = stringResource(R.string.note),
                    fontFamily = FontFamily(Font(R.font.amiko_bold)),
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .shadow(4.dp, RoundedCornerShape(0.dp)),
            actions = {
                OutlinedButton(
                    onClick = {
                        viewModel.setEvent(NoteDetailsContract.Event.OnSaveNote)
                        navigator.back()
                        Toast.makeText(context, "Saved successfully", Toast.LENGTH_SHORT).show()
                    }
                ) {
                    Icon(
                        painter = rememberAsyncImagePainter(R.drawable.ic_save),
                        contentDescription = "Save note"
                    )
                }
            }
        )
    }

    @Composable
    fun EditNoteBottomBar(
        note: NoteDBO,
        navigator: NavigationContainer<StackState>,
    ) {
        val context = LocalContext.current
        val viewModel: NoteDetailsViewModel = koinViewModel()

        BottomAppBar(
            modifier = Modifier.height(64.dp)
        ) {
            IconButton(
                onClick = {
                    shareNote(context, note)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "select notify date"
                )
            }
            Text(
                text = note.updatedAt,
                fontFamily = FontFamily(Font(R.font.amiko_semi_bold)),
                textAlign = TextAlign.Center,
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
                        isMenuExpanded = isMenuExpanded.not()
                    },
                    content = {
                        DropdownMenuItem(
                            onClick = {
                                isMenuExpanded = isMenuExpanded.not()
                                navigator.back()
                                viewModel.setEvent(NoteDetailsContract.Event.OnDeleteNote(note))
                            },
                            text = {
                                Text("Delete")
                            }
                        )
                    }
                )
            }

        }
    }
}

fun shareNote(context: Context, note: NoteDBO) {
    val shareMsg =
        "Hey, I want to share this note with you:\n\nTitle: ${note.title}\n\nNote: ${note.description}"

    val intent = ShareCompat.IntentBuilder(context)
        .setType("text/plain")
        .setText(shareMsg)
        .intent
    context.startActivity(Intent.createChooser(intent, null))
}