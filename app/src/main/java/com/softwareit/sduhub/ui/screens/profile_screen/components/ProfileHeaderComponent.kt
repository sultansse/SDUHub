package com.softwareit.sduhub.ui.screens.profile_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.softwareit.sduhub.R
import com.softwareit.sduhub.data.network.backend.Student

@Composable
fun ProfileHeaderComponent(
    student: Student,
    onClick: () -> Unit
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .clip(RoundedCornerShape(16.dp))
        .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(16.dp))
        .clickable { onClick() }) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(32.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.img_mysdu),
                contentDescription = "User profile picture",
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp), modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = student.fullname,
                    fontFamily = FontFamily(Font(R.font.amiko_bold)),
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = student.studentId.toString(),
                    fontFamily = FontFamily(Font(R.font.amiko_semi_bold)),
                    modifier = Modifier.fillMaxWidth()

                )
                Text(
                    text = "Student",
                    fontFamily = FontFamily(Font(R.font.amiko_regular)),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = "Go to profile settings",
            )
        }
    }
}