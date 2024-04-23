package com.softwareit.sduhub.ui.screens.home_screen.categories.services.faculties_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.softwareit.sduhub.ui.model.FacultyDIO
import com.softwareit.sduhub.ui.screens.home_screen.categories.services.faculties_screen.FacultiesContract
import com.softwareit.sduhub.ui.theme.colorSduLightGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FacultyDialogComponent(faculty: FacultyDIO, onUiEvent: (FacultiesContract.Event) -> Unit) {


    BasicAlertDialog(
        onDismissRequest = { onUiEvent(FacultiesContract.Event.EmptyEffect) }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(colorSduLightGray)
                .verticalScroll(rememberScrollState())
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
            faculty.facultyContacts.forEach { contact ->
                Text(
                    text = contact.contactName,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                Text(
                    text = contact.contactRole,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
    }
}