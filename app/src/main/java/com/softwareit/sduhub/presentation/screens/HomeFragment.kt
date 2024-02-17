package com.softwareit.sduhub.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import com.example.compose.SDUHubTheme
import com.softwareit.sduhub.R

class HomeFragment : Fragment() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setContent {
            SDUHubTheme {
                Surface {
                    Scaffold(
                        topBar = {
                            TopAppBar(title = { Text(text = stringResource(R.string.app_name)) })
                        }
                    ) {
                        Box(modifier = Modifier.padding(it)) {
                            HomeScreen()
                        }

                    }

                }
            }


        }
    }

    @Composable
    fun HomeScreen() {
        Text(text = "Home Screen")
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeFragment().HomeScreen()
}
