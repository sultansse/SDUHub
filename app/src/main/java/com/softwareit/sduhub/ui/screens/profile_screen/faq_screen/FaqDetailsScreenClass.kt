package com.softwareit.sduhub.ui.screens.profile_screen.faq_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.terrakok.modo.LocalContainerScreen
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.StackScreen
import com.github.terrakok.modo.stack.back
import com.softwareit.sduhub.R
import com.softwareit.sduhub.domain.faq_usecase.FaqDTO
import com.softwareit.sduhub.utils.common.data.network.getLocalMessage
import com.softwareit.sduhub.utils.common.openGmail
import com.softwareit.sduhub.utils.common.presentation.GenericLottieAnimationComponent
import com.softwareit.sduhub.utils.common.presentation.LoadingLottieComponent
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel

@Parcelize
class FaqDetailsScreenClass(
    override val screenKey: ScreenKey = generateScreenKey(),
) : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val parent = LocalContainerScreen.current
        val parentScreen = parent as StackScreen
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.faq)) },
                    navigationIcon = {
                        IconButton(onClick = { parentScreen.back() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) {
            Box(modifier = Modifier.padding(it)) {
                FaqScreen()
            }
        }
    }

    @Composable
    fun FaqScreen() {

        val viewModel: FaqDetailsViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()

        LaunchedEffect(key1 = true) {
            viewModel.setEvent(FaqDetailsContract.Event.OnFetchFaqItems)
        }

        Column {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.weight(1f)
            ) {
                item {
                    FaqHeader()
                }

                when (val state = uiState.faqState) {
                    is FaqDetailsContract.FaqState.Loading -> {
                        item { LoadingLottieComponent() }
                    }

                    is FaqDetailsContract.FaqState.Success -> {
                        items(state.faqItems.size, key = { state.faqItems[it].id }) { index ->
                            FaqItem(state.faqItems[index])
                        }
                    }

                    is FaqDetailsContract.FaqState.Error -> {
                        item {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 64.dp)
                            ) {
                                Text(
                                    text = state.exception.getLocalMessage(LocalContext.current),
                                    fontFamily = FontFamily(Font(R.font.amiko_bold)),
                                )
                                GenericLottieAnimationComponent(R.raw.anim_error_occured)
                            }
                        }
                    }
                }
            }
            ContactUsItem()
        }
    }

    @Composable
    private fun ContactUsItem() {
        val context = LocalContext.current
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            Text(
                text = stringResource(R.string.still_have_questions),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.amiko_bold)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
            Button(
                onClick = {
                      openGmail(
                          context,
                          to = "200107106@stu.sdu.edu.kz",
                          subject = "Your subject",
                          body = "Hello SDUHUB! I have a question about..."
                      )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.contact_us),
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.amiko_semi_bold)),
                )
            }
        }
    }

    @Composable
    private fun FaqItem(faqItem: FaqDTO) {
        var isExpanded by remember { mutableStateOf(false) }
        val rotationAngle by animateFloatAsState(
            targetValue = if (isExpanded) 180f else 0f,
            label = if (isExpanded) "Collapse" else "Expand"
        )

        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isExpanded = isExpanded.not() }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Text(
                        text = faqItem.question,
                        modifier = Modifier.weight(1f),
                        fontFamily = FontFamily(Font(R.font.amiko_semi_bold)),
                        fontSize = 20.sp,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = if (isExpanded) "Collapse" else "Expand",
                        modifier = Modifier.rotate(rotationAngle),
                    )
                }
            }
            // Only show this if isExpanded is true
            AnimatedVisibility(visible = isExpanded) {
                Text(
                    text = faqItem.answer,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.amiko_regular)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
        }
    }

    @Composable
    private fun FaqHeader() {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Frequently Asked Questions",
                fontSize = 32.sp,
                lineHeight = 40.sp,
                fontFamily = FontFamily(Font(R.font.amiko_bold)),
                textAlign = TextAlign.Center,
            )
        }

    }

}
