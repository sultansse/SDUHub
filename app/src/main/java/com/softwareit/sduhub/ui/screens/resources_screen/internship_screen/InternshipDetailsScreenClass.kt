package com.softwareit.sduhub.ui.screens.resources_screen.internship_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.terrakok.modo.LocalContainerScreen
import com.github.terrakok.modo.NavigationContainer
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.StackScreen
import com.github.terrakok.modo.stack.StackState
import com.github.terrakok.modo.stack.back
import com.softwareit.sduhub.R
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel

@Parcelize
class InternshipDetailsScreenClass(
    private val internshipId: Int,
    override val screenKey: ScreenKey = generateScreenKey(),
) : Screen {

    @Composable
    override fun Content() {

        val parent = LocalContainerScreen.current

        Scaffold(
            topBar = { InternshipTopBar(parent as StackScreen) }
        ) {
            Box(
                modifier = Modifier.padding(it)
            ) {
                CurrentInternshipScreen()
            }
        }
    }

    @Composable
    private fun CurrentInternshipScreen() {

        val viewModel: InternshipScreenViewModel = koinViewModel()

        LaunchedEffect(key1 = true) {
            viewModel.setEvent(InternshipContract.Event.OnFetchInternship(internshipId))
        }

        val state by viewModel.uiState.collectAsState()

        when (val internshipState = state.internshipState) {
            is InternshipContract.InternShipState.Success -> {
                val internship = internshipState.data
                // Internship details
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Text(
                            text = internship.title,
                            fontSize = 24.sp,
                            fontWeight = Bold,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = "Pay: ${internship.salary} KZT",
                            fontSize = 20.sp,
                            fontWeight = Bold,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Text(
                        text = "Format: ${internship.format}",
                        fontSize = 16.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "Duration: ${internship.duration}",
                        fontSize = 16.sp,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.LightGray, shape = RoundedCornerShape(8.dp))
                            .padding(8.dp)
                    ) {
                        Column {
                            Text(
                                text = "Company: ${internship.company}",
                                fontSize = 20.sp,
                                fontWeight = Bold,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Text(
                                text = "Location: ${internship.location}",
                                fontSize = 16.sp,
                                fontWeight = Bold,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Text(
                                text = "Apply Deadline: ${internship.applyDeadline}",
                                fontSize = 16.sp,
                                fontWeight = Bold,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }

                    Text(
                        text = internship.description,
                        modifier = Modifier.fillMaxWidth()
                            .padding(vertical = 16.dp)
                    )

                    val contactsText = buildAnnotatedString {
                        append("Contacts: ")
                        pushStringAnnotation(tag = "policy", annotation = "https://t.me/sduhub")
                        withStyle(style = SpanStyle(color = Color.Blue)) {
                            append(internship.contacts)
                        }
                    }
                    Text(
                        text = contactsText,
                        modifier = Modifier.fillMaxWidth()
                    )

                }
            }

            is InternshipContract.InternShipState.Idle -> {
                // Loading
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun InternshipTopBar(navigator: NavigationContainer<StackState>) {

        // Top bar
        TopAppBar(
            title = {
                Text(text = stringResource(R.string.internship_vacancy))
            },
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
            modifier = Modifier
                .fillMaxWidth()
                .shadow(4.dp, RoundedCornerShape(0.dp)),
        )

    }
}