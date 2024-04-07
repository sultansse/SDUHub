package com.softwareit.sduhub.ui.screens.profile_screen.components

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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.rememberAsyncImagePainter
import com.softwareit.sduhub.R
import com.softwareit.sduhub.ui.model.StudentDIO
import com.softwareit.sduhub.ui.theme.colorSduBlue
import com.softwareit.sduhub.ui.theme.colorSduOrange
import com.softwareit.sduhub.ui.theme.colorWhite


@Composable
fun ProfileIdCardDialog(
    student: StudentDIO,
    onClose: () -> Unit,
) {
    Dialog(
        onDismissRequest = { onClose() },
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorSduBlue, shape = RoundedCornerShape(32.dp)
                )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.verticalScroll(rememberScrollState()),
            ) {
                Text(
                    text = "SDU UNIVERSITY",
                    color = colorWhite,
                    fontFamily = FontFamily(Font(R.font.abhaya_libre_extra_bold)),
                    fontSize = 32.sp,
                    modifier = Modifier.padding(32.dp)
                )
                Image(
                    painter = rememberAsyncImagePainter(R.drawable.img_sdukz),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Student image",
                    modifier = Modifier.size(160.dp, 200.dp)
                )
                Text(
                    text = "STUDENT",
                    color = colorSduOrange,
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.abhaya_libre_extra_bold)),
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    text = student.fullname,
                    color = colorWhite,
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.amiko_bold)),
                    modifier = Modifier.padding(vertical = 16.dp)

                )
                HorizontalDivider(
                    thickness = 1.dp, color = Color.White
                )
                Text(
                    text = student.faculty,
                    color = colorSduOrange,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.amiko_bold)),
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                HorizontalDivider(
                    thickness = 1.dp, color = Color.White
                )
                Text(
                    text = "Жеке нөмірі | ID Number",
                    color = colorWhite,
                    fontFamily = FontFamily(Font(R.font.amiko_bold)),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 16.dp, bottom = 4.dp)
                )
                Text(
                    text = student.studentId.toString(),
                    color = colorWhite,
                    fontFamily = FontFamily(Font(R.font.amiko_bold)),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

            }
        }
    }
}
