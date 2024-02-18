package com.softwareit.sduhub.presentation.screens.home_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.fragment.app.Fragment
import androidx.fragment.compose.content
import com.example.compose.SDUHubTheme
import com.softwareit.sduhub.R
import com.softwareit.sduhub.presentation.screens.home_screen.components.Categories
import com.softwareit.sduhub.presentation.screens.home_screen.components.Stories
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
                        title = { Text(text = stringResource(R.string.app_name), fontWeight = FontWeight.Bold) }
                    )
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
        Categories()
//        if (any warning data) {
//            WarningData()
//        }
//        add list of notes

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
