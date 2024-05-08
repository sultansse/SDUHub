package com.softwareit.sduhub.ui.screens.profile_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.softwareit.sduhub.R
import com.softwareit.sduhub.data.local.datastore.DataStoreUtil
import com.softwareit.sduhub.ui.screens.profile_screen.ProfileScreenContract
import com.softwareit.sduhub.ui.screens.profile_screen.ProfileScreenViewModel
import com.softwareit.sduhub.ui.theme.colorSduBlue
import com.softwareit.sduhub.ui.theme.colorSduDarkGray
import com.softwareit.sduhub.ui.theme.colorSduLightBlue
import com.softwareit.sduhub.ui.theme.colorSduLightGray
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AuthDialogComponent() {
    val viewModel: ProfileScreenViewModel = koinViewModel()
    val dataStoreUtil: DataStoreUtil = koinInject()
    val isDarkThemeEnabled by dataStoreUtil.getTheme()
        .collectAsState(initial = isSystemInDarkTheme())


    BasicAlertDialog(onDismissRequest = {
        viewModel.setEvent(ProfileScreenContract.Event.EmptyEffect)
    }) {
        val boxBackgroundColor = if (isDarkThemeEnabled) {
            colorSduDarkGray
        } else {
            colorSduLightGray
        }

        val boxBorderColor = if (isDarkThemeEnabled) {
            colorSduLightBlue
        } else {
            colorSduBlue
        }

        Column(
            modifier = Modifier
                .background(boxBackgroundColor, RoundedCornerShape(8.dp))
                .border(1.dp, boxBorderColor, RoundedCornerShape(8.dp))
                .padding(16.dp),
        ) {
            var username by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var isPassVisible by remember { mutableStateOf(false) }
            val viewPassIcon = remember(isPassVisible) {
                if (isPassVisible) {
                    R.drawable.ic_visibility_off
                } else {
                    R.drawable.ic_visibility_on
                }
            }

            Text(
                text = stringResource(R.string.auth_dialog_title),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = stringResource(R.string.auth_dialog_description),
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = username,
                onValueChange = { username = it.trim() },
                singleLine = true,
                label = { Text(text = "ID number") },
                modifier = Modifier.padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it.trim() },
                singleLine = true,
                label = { Text(text = "Password") },
                trailingIcon = {
                    IconButton(onClick = { isPassVisible = !isPassVisible }) {
                        Icon(
                            painter = painterResource(id = viewPassIcon),
                            contentDescription = "View password"
                        )
                    }
                },
                visualTransformation = if (isPassVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        viewModel.setEvent(ProfileScreenContract.Event.EmptyEffect)
                    },
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text(text = stringResource(R.string.cancel))
                }
                Button(
                    onClick = {
                        viewModel.setEvent(
                            ProfileScreenContract.Event.OnSubmitAuth(
                                username = username,
                                password = password,
                            )
                        )
                    },
                ) {
                    Text(text = stringResource(R.string.login))
                }
            }
        }
    }
}