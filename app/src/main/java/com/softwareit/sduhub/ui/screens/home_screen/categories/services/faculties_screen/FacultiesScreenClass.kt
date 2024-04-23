package com.softwareit.sduhub.ui.screens.home_screen.categories.services.faculties_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.github.terrakok.modo.LocalContainerScreen
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.StackScreen
import com.github.terrakok.modo.stack.back
import com.softwareit.sduhub.R
import com.softwareit.sduhub.ui.model.ElementDIO
import com.softwareit.sduhub.ui.screens.home_screen.categories.services.faculties_screen.components.FacultyDialogComponent
import com.softwareit.sduhub.ui.theme.colorSduBlue
import io.woong.compose.grid.SimpleGridCells
import io.woong.compose.grid.VerticalGrid
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel


private val faculties = listOf(
    ElementDIO(
        id = 0,
        icon = R.drawable.img_faculty_business,
        title = "Business school" // todo stringres
    ),
    ElementDIO(
        id = 1,
        icon = R.drawable.img_faculty_engineering,
        title = "Engineering and Natural Sciences" // todo stringres
    ),
    ElementDIO(
        id = 2,
        icon = R.drawable.img_faculty_law,
        title = "Law and Social Sciences" // todo stringres
    ),
    ElementDIO(
        id = 3,
        icon = R.drawable.img_faculty_education,
        title = "Education and Humanities" // todo stringres
    ),
)


@Parcelize
class FacultiesScreenClass(
    override val screenKey: ScreenKey = generateScreenKey(),
) : Screen {

    @[Composable ExperimentalMaterial3Api]
    override fun Content() {
        val parent = LocalContainerScreen.current
        val parentScreen = parent as StackScreen

        val viewModel: FacultiesViewModel = koinViewModel()
        val uiEffect by viewModel.effect.collectAsState(initial = FacultiesContract.Effect.Idle)

        when (val effect = uiEffect) {
            FacultiesContract.Effect.Idle -> {
                // do nothing
            }

            is FacultiesContract.Effect.FacultyDialog -> {
                FacultyDialogComponent(effect.faculty, viewModel::setEvent)
            }
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Faculties") },
                    navigationIcon = {
                        IconButton(onClick = { parentScreen.back() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) {
            Box(modifier = Modifier.padding(it)) {
                FacultiesScreen(viewModel::setEvent)
            }
        }
    }

    @Composable
    fun FacultiesScreen(onUiEvent: (FacultiesContract.Event) -> Unit) {
        VerticalGrid(
            columns = SimpleGridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            repeat(faculties.size) {
                FacultyItem(
                    ElementDIO(
                        id = faculties[it].id,
                        icon = faculties[it].icon,
                        title = faculties[it].title
                    ),
                    onUiEvent = onUiEvent,
                )
            }
        }
    }

    @Composable
    fun FacultyItem(
        faculty: ElementDIO,
        onUiEvent: (FacultiesContract.Event) -> Unit,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .clickable {
                    when (faculty.id) {
                        0 -> onUiEvent(
                            FacultiesContract.Event.OnFacultyClick(facultyBusinessSchool)
                        )

                        1 -> onUiEvent(
                            FacultiesContract.Event.OnFacultyClick(facultyEngineeringAndNaturalSciences)
                        )

                        2 -> onUiEvent(
                            FacultiesContract.Event.OnFacultyClick(facultyOfLawAndSocialSciences)
                        )

                        3 -> onUiEvent(
                            FacultiesContract.Event.OnFacultyClick(facultyEducationAndHumanities)
                        )
                    }
                }
                .padding(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(faculty.icon),
                contentDescription = "Service: ${faculty.title}",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .clipToBounds()
                    .background(colorSduBlue, RoundedCornerShape(16.dp))
                    .height(220.dp)
                    .padding(8.dp)

            )
            Text(
                text = faculty.title,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}