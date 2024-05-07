package com.softwareit.sduhub.ui.screens.home_screen.categories.services.student_clubs_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.github.terrakok.modo.LocalContainerScreen
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.StackScreen
import com.github.terrakok.modo.stack.back
import com.softwareit.sduhub.common.presentation.openWebsite
import com.softwareit.sduhub.ui.model.StudentClubDIO
import com.softwareit.sduhub.ui.screens.home_screen.categories.services.student_clubs_screen.components.StudentClubBottomSheetComponent
import com.softwareit.sduhub.ui.theme.colorSduDarkGray
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel

@Parcelize
class StudentClubsScreenClass(
    override val screenKey: ScreenKey = generateScreenKey(),
) : Screen {

    @[Composable ExperimentalMaterial3Api]
    override fun Content() {
        val parent = LocalContainerScreen.current
        val parentScreen = parent as StackScreen
        val context = LocalContext.current
        val viewModel: StudentClubsViewModel = koinViewModel()

        val uiEffect by viewModel.effect.collectAsState(initial = StudentClubsContract.Effect.Idle)

        when (val effect = uiEffect) {
            is StudentClubsContract.Effect.Idle -> {
                // do nothing
            }
            is StudentClubsContract.Effect.StudentClubDialog -> {
                StudentClubBottomSheetComponent(effect.studentClub, viewModel::setEvent)
            }
            is StudentClubsContract.Effect.ApplyGoogleForms -> {
                openWebsite(context, "https://docs.google.com/forms/d/e/1FAIpQLSfNn9rhtOFLDZerT6jhtqR0S1fosnnrDh57aKRX1hzjW32itw/viewform")
            }
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Student clubs") },
                    navigationIcon = {
                        IconButton(onClick = { parentScreen.back() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) {
            Box(modifier = Modifier.padding(it)) {
                StudentClubsScreen(viewModel::setEvent)
            }
        }
    }

    @Composable
    fun StudentClubsScreen(onUiEvent: (StudentClubsContract.Event) -> Unit) {
        LazyColumn {
            items(studentClubs.size, key = { studentClubs[it].name }) { index ->
                StudentClubItem(
                    studentClub = studentClubs[index],
                    onUiEvent = onUiEvent,
                )
            }
        }
    }

    @Composable
    fun StudentClubItem(
        studentClub: StudentClubDIO,
        onUiEvent: (StudentClubsContract.Event) -> Unit,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable { onUiEvent(StudentClubsContract.Event.OnStudentClubClick(studentClub)) }
                .border(width = 1.dp, color = colorSduDarkGray, shape = RoundedCornerShape(8.dp))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(studentClub.imageResId),
                    contentDescription = "Student club image",
                    modifier = Modifier.size(72.dp)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Text(
                        text = studentClub.name,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = studentClub.description,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}