package com.softwareit.sduhub.ui.screens.home_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.fragment.app.Fragment
import androidx.fragment.compose.content
import com.softwareit.sduhub.R
import com.softwareit.sduhub.ui.base.SIDE_EFFECTS_KEY
import com.softwareit.sduhub.ui.screens.home_screen.components.Categories
import com.softwareit.sduhub.ui.screens.home_screen.components.Notes
import com.softwareit.sduhub.ui.screens.home_screen.components.Stories
import com.softwareit.sduhub.ui.theme.SDUHubTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val viewModel: HomeScreenViewModel by viewModel()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = content {
        SDUHubTheme {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = stringResource(R.string.app_name),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    )
                },
                floatingActionButtonPosition = FabPosition.End,
                floatingActionButton = {
                    FloatingActionButton(
                        shape = CircleShape,
                        onClick = {
                            Toast.makeText(requireContext(), "fab is clicked", Toast.LENGTH_LONG).show()
                        }
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = "Add")
                    }
                },
            ) {
                Box(modifier = Modifier.padding(it)) {
                    HomeScreen(
                        state = viewModel.viewState.value,
                        effectFlow = viewModel.effect,
                        onEventSent = { event -> viewModel.setEvent(event) }
                    )
                }
            }
        }
    }

    @Composable
    fun HomeScreen(
        state: HomeContract.State,
        effectFlow: Flow<HomeContract.Effect>?,
        onEventSent: (event: HomeContract.Event) -> Unit,
    ) {

//        val importantInfo by viewModel.data.collectAsState()

        LaunchedEffect(SIDE_EFFECTS_KEY) {
            effectFlow?.onEach { effect ->
                when (effect) {
                    is HomeContract.Effect.ShowError -> {
                        Toast.makeText(requireContext(),"show error", Toast.LENGTH_LONG).show()
                    }

                }
            }?.collect()
        }

        Column {
            Stories()
            Categories()
//            AnimatedVisibility(visible = (importantInfo != null)) {
//                ImportantInfo(data = importantInfo!!)
//            }
            Text(text = "Home Screen")
            Button(
                onClick = {
                    viewModel.setEvent(HomeContract.Event.OnNoteAdded())
//                viewModel.goToCategory()
//                    viewModel.setData(ImportantInfoDTO("some title 1", "some description 2" ))
                }
            ) {
                Text(text = "save to db")
            }

            Notes(notes = state.notes)
        }
    }

}

