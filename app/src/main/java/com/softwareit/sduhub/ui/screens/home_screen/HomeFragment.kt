package com.softwareit.sduhub.ui.screens.home_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.fragment.app.Fragment
import androidx.fragment.compose.content
import com.example.compose.SDUHubTheme
import com.softwareit.sduhub.R
import com.softwareit.sduhub.ui.screens.home_screen.components.Categories
import com.softwareit.sduhub.ui.screens.home_screen.components.ImportantInfo
import com.softwareit.sduhub.ui.screens.home_screen.components.ImportantInfoDTO
import com.softwareit.sduhub.ui.screens.home_screen.components.Stories
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

    val importantInfo by viewModel.data.collectAsState()

    Column {
        Stories()
        Categories()
        AnimatedVisibility(visible = (importantInfo != null)) {
            ImportantInfo(data = importantInfo!!)
        }
        if (importantInfo != null) {
            Text(text = "Loading or No Data")
        }

        Text(text = "Home Screen")
        Button(
            onClick = {
//                viewModel.goToCategory()
                viewModel.setData(ImportantInfoDTO("some title 1", "some description 2" ))
            }
        ) {
            Text(text = "save to db")
        }

        Button(
            onClick = {
                viewModel.getData()
            }
        ) {
            Text("get data")
        }
    }


}
