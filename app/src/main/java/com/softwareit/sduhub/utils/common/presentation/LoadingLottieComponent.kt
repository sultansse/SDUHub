package com.softwareit.sduhub.utils.common.presentation

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.softwareit.sduhub.R

@Composable
fun LoadingLottieComponent(@RawRes loadingAnimation: Int = R.raw.anim_squares_loading) {
    GenericLottieAnimationComponent(
        animationResource = loadingAnimation,
    )
}

@Composable
fun GenericLottieAnimationComponent(
    @RawRes animationResource: Int,
    modifier: Modifier = Modifier,
    iterations: Int = Int.MAX_VALUE,
    contentAlignment: Alignment = Alignment.Center,
    lottieAlignment: Alignment = Alignment.TopCenter
) {
    Box(
        contentAlignment = contentAlignment,
        modifier = modifier.fillMaxSize()
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animationResource))
        LottieAnimation(
            composition = composition,
            iterations = iterations,
            alignment = lottieAlignment
        )
    }
}
