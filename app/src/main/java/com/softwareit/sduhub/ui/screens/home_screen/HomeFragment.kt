package com.softwareit.sduhub.ui.screens.home_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.compose.content
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.softwareit.sduhub.R
import com.softwareit.sduhub.common.utils.getFormattedTime
import com.softwareit.sduhub.common.utils.isNotNull
import com.softwareit.sduhub.data.local.notes.NoteDTO
import com.softwareit.sduhub.ui.screens.home_screen.components.Categories
import com.softwareit.sduhub.ui.screens.home_screen.components.ImportantInfo
import com.softwareit.sduhub.ui.screens.home_screen.components.NoteItem
import com.softwareit.sduhub.ui.screens.home_screen.components.Stories
import com.softwareit.sduhub.ui.theme.SDUHubTheme
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val viewModel: HomeScreenViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = content {
        SDUHubTheme {

            Scaffold(
                topBar = { HomeTopAppBar() },
                floatingActionButton = { HomeAddNoteFAB() },
            ) {
                Box(
                    modifier = Modifier.padding(it)
                ) {
                    HomeScreen(viewModel)
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun HomeTopAppBar() {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.app_name),
                    fontWeight = FontWeight.Bold
                )
            }
        )
    }

    @Composable
    fun HomeScreen(viewModel: HomeScreenViewModel) {

        val state = viewModel.uiState.collectAsState().value

        LazyColumn {

            item { Stories() }
            item { Categories() }

            when (state.importantInfoState) {
                is HomeContract.ImportantInfoState.Success -> {
                    item {
                        AnimatedVisibility(visible = (state.importantInfoState.data.isNotNull())) {
                            ImportantInfo(data = state.importantInfoState.data)
                        }
                    }
                }

                is HomeContract.ImportantInfoState.Idle -> {
                    item {
                        Text(
                            text = "Welcome back dear user!",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            modifier = Modifier.padding(20.dp)
                        )
                    }
                }
            }

            when (state.notesState) {
                is HomeContract.NotesState.Success -> {
                    items(state.notesState.data) { note ->
                        AnimatedVisibility(
                            visible = true,
                            enter = fadeIn(tween(300)) +
                                    expandVertically(tween(300)),
                            exit = fadeOut(tween(300))
                        ) {
                            NoteItem(note = note)
                        }
                    }
                }

                is HomeContract.NotesState.Idle -> {
                    item {
                        val composition =
                            rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.anim_not_found))
                        LottieAnimation(
                            composition = composition.value,
                            iterations = Int.MAX_VALUE,
                            alignment = Alignment.TopCenter
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun HomeAddNoteFAB() {
        var counter by remember {
            mutableIntStateOf(0)
        }
        FloatingActionButton(
            shape = CircleShape,
            onClick = {
                counter++
                val data = NoteDTO(
                    title = "My #$counter note title",
                    description = "Some description to my note to check how it looks in layout",
                    created_at = getFormattedTime()
                )
                viewModel.setEvent(HomeContract.Event.OnNoteAdded(data))
            }
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add")
        }
    }
}