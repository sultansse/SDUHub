package com.softwareit.sduhub.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.fragment.compose.content
import androidx.lifecycle.viewmodel.compose.viewModel
import com.appsamurai.storyly.StoryGroupSize
import com.appsamurai.storyly.StorylyInit
import com.appsamurai.storyly.StorylyView
import com.appsamurai.storyly.config.StorylyConfig
import com.appsamurai.storyly.config.styling.group.StorylyStoryGroupStyling
import com.example.compose.SDUHubTheme
import com.softwareit.sduhub.R
import com.softwareit.sduhub.presentation.utils.Constants.Companion.STORYLY_INSTANCE_TOKEN
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
                    TopAppBar(title = { Text(text = stringResource(R.string.app_name)) })
                }
            ) {
                Box(modifier = Modifier.padding(it)) {
                    HomeScreen(viewModel)
                }
            }
        }
    }
}


@Composable
fun HomeScreen(viewModel: HomeScreenViewModel) {

    Column {
        Stories()
        Text(text = "Home Screen")
        Button(
            onClick = {
                viewModel.goToCategory()
            }
        ) {
            Text(text = "Click me")
        }
    }

}

@Composable
fun Stories() {
    Row {
        AndroidView(
            factory = { context ->
                StorylyView(context).apply {
                    storylyInit = StorylyInit(
                        storylyId = STORYLY_INSTANCE_TOKEN,
                        config = StorylyConfig
                            .Builder()
                            .setStoryGroupStyling(
                                styling = StorylyStoryGroupStyling
                                    .Builder()
                                    .setTitleVisibility(isVisible = false)
                                    .setSize(
                                        size = StoryGroupSize.Custom
                                    )
                                    .setIconCornerRadius(60)
                                    .setIconHeight(600)
                                    .setIconWidth(540)
                                    .build()
                            )
                            .build()
                    )

                }
            }
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreenPreview() {
     val viewModel: HomeScreenViewModel = viewModel()
    HomeScreen(viewModel)
}
