package com.softwareit.sduhub.utils.common.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.softwareit.sduhub.R

@Composable
fun LoadingMarioComponent() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        val composition = rememberLottieComposition(
            LottieCompositionSpec.RawRes(R.raw.anim_mario_loading)
        )
        LottieAnimation(
            composition = composition.value,
            iterations = Int.MAX_VALUE,
            alignment = Alignment.TopCenter
        )
    }
}