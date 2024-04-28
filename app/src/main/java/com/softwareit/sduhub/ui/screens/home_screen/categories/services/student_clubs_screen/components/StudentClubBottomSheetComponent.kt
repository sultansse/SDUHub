package com.softwareit.sduhub.ui.screens.home_screen.categories.services.student_clubs_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.softwareit.sduhub.R
import com.softwareit.sduhub.ui.model.StudentClubDIO
import com.softwareit.sduhub.ui.screens.home_screen.categories.services.student_clubs_screen.StudentClubsContract
import com.softwareit.sduhub.ui.theme.colorSduDarkGray

@[Composable OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)]
fun StudentClubBottomSheetComponent(
    studentClub: StudentClubDIO,
    onUiEvent: (StudentClubsContract.Event) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        onDismissRequest = { onUiEvent(StudentClubsContract.Event.EmptyEffect) },
        sheetState = sheetState,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp)
                .border(width = 1.dp, color = colorSduDarkGray)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(studentClub.imageResId),
                    contentDescription = "Student club image",
                    modifier = Modifier.size(240.dp)
                )
                Text(
                    text = studentClub.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                )
                Text(
                    text = studentClub.longDescription,
                )
                Button(
                    onClick = { onUiEvent(StudentClubsContract.Event.OnApplyToClub(studentClub.name)) }
                ) {
                    Text(
                        text = stringResource(R.string.apply)
                    )
                }
            }
        }
    }
}