package com.softwareit.sduhub.ui.screens.home_screen.categories.services.faculties_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.rememberAsyncImagePainter
import com.softwareit.sduhub.ui.model.FacultyDIO
import com.softwareit.sduhub.ui.screens.home_screen.categories.services.faculties_screen.FacultiesContract
import com.softwareit.sduhub.ui.theme.colorSduLightGray

@Composable
fun FacultyDialogComponent(
    faculty: FacultyDIO,
    onUiEvent: (FacultiesContract.Event) -> Unit,
) {
    Dialog(
        onDismissRequest = { onUiEvent(FacultiesContract.Event.EmptyEffect) },
    ) {
        val configuration = LocalConfiguration.current
        val dialogWidth =
            remember { (configuration.screenWidthDp.dp) * 0.76f }
        val dialogHeight =
            remember { (configuration.screenHeightDp.dp) * 0.82f }

        Box(
            modifier = Modifier
                .size(width = dialogWidth, height = dialogHeight)
                .clip(RoundedCornerShape(16.dp))
                .background(colorSduLightGray)
                .padding(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(colorSduLightGray)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                Text(
                    text = faculty.facultyName,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                Image(
                    painter = rememberAsyncImagePainter(faculty.deanImage),
                    contentDescription = "Dean's image",
                    modifier = Modifier.size(200.dp)
                )
                Text(
                    text = faculty.deanName,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                Text(
                    text = faculty.facultyDescription,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }

    }
}