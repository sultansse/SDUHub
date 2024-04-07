package com.softwareit.sduhub.ui.screens.profile_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.softwareit.sduhub.R
import com.softwareit.sduhub.ui.model.StudentDIO
import com.softwareit.sduhub.utils.common.presentation.GenericLottieAnimationComponent

@Composable
fun ProfileHeaderComponent(
    apiStudent: StudentDIO,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
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
                    text = apiStudent.fullname,
                    fontFamily = FontFamily(Font(R.font.amiko_bold)),
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = apiStudent.studentId.toString(),
                    fontFamily = FontFamily(Font(R.font.amiko_semi_bold)),
                    modifier = Modifier.fillMaxWidth()

                )
                Text(
                    text = stringResource(R.string.student),
                    fontFamily = FontFamily(Font(R.font.amiko_regular)),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = "Go to profile settings",
            )
        }
        Spacer(modifier = Modifier.height(118.dp))
    }
}

@Composable
fun ProfileHeaderIdleComponent(onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(16.dp))
            .clickable { onClick() }
    ) {
        Text(
            text = "Auth user",
            fontFamily = FontFamily(Font(R.font.amiko_bold)),
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(42.dp) // todo remove since its specific values, it should be dynamic
        )
        Spacer(modifier = Modifier.height(118.dp))
    }
}

@Composable
fun ProfileHeaderLoadingComponent() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(16.dp))
    ) {
        Text(
            text = stringResource(R.string.loading),
            fontFamily = FontFamily(Font(R.font.amiko_bold)),
            fontSize = 24.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        GenericLottieAnimationComponent(
            animationResource = R.raw.anim_cat_watching,
            modifier = Modifier.height(118.dp)
        )
        Spacer(modifier = Modifier.height(118.dp))
    }
}

@Composable
fun ProfileHeaderErrorComponent(onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(16.dp))
            .clickable { onClick() }
    ) {
        Text(
            text = stringResource(R.string.error_occurred_click_to_retry),
            fontFamily = FontFamily(Font(R.font.amiko_bold)),
            fontSize = 24.sp,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(118.dp))
    }
}