package com.softwareit.sduhub.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.fragment.app.Fragment
import androidx.fragment.compose.content
import com.softwareit.sduhub.common.presentation.hideKeyboardOnOutsideClick
import com.softwareit.sduhub.ui.theme.SduHubTheme

abstract class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = content {
        SduHubTheme {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .hideKeyboardOnOutsideClick()
            ) {
                SetContent()
            }
        }
    }

    @Composable
    abstract fun SetContent()

}