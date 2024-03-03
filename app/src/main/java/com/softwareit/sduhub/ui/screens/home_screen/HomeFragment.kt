package com.softwareit.sduhub.ui.screens.home_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.softwareit.sduhub.R
import com.softwareit.sduhub.base.BaseFragment
import com.softwareit.sduhub.ui.screens.home_screen.components.Categories
import com.softwareit.sduhub.ui.screens.home_screen.components.ImportantInfo
import com.softwareit.sduhub.ui.screens.home_screen.components.NotesComponent
import com.softwareit.sduhub.ui.screens.home_screen.components.Stories
import com.softwareit.sduhub.utils.isNotNull
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment() {

    private val viewModel: HomeScreenViewModel by viewModel()

    @Composable
    override fun SetContent() {
        Scaffold(
            topBar = { HomeTopAppBar() },
            floatingActionButton = { HomeAddNoteFAB() },
        ) {
            Box(
                modifier = Modifier.padding(it)
            ) {
                HomeScreen()
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
    fun HomeAddNoteFAB() {
        FloatingActionButton(
            shape = CircleShape,
            onClick = {
                viewModel.goToEditNote()
            }
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add")
        }
    }

    @Composable
    fun HomeScreen() {

        val uiState by viewModel.uiState.collectAsState()

        LazyColumn {

            item { Stories() }

            item { Categories(viewModel) }

            item {
                when (val state = uiState.importantInfoState) {
                    is HomeContract.ImportantInfoState.Success -> {
                        AnimatedVisibility(visible = (state.data.isNotNull())) {
                            ImportantInfo(data = state.data)
                        }
                    }

                    is HomeContract.ImportantInfoState.Idle -> {
                        Text(
                            text = "Welcome back dear user!",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            modifier = Modifier.padding(20.dp)
                        )
                    }
                }
            }

            item {
                when (val state = uiState.notesState) {
                    is HomeContract.NotesState.Success -> {
                        NotesComponent(notes = state.data)
                    }

                    is HomeContract.NotesState.Idle -> {
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

    @Preview(showSystemUi = true)
    @Composable
    fun HomeScreenPreview() {
        SetContent()
    }
}