package com.softwareit.sduhub.application

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.github.terrakok.modo.ComposeRendererScope
import com.github.terrakok.modo.DialogScreen
import com.github.terrakok.modo.ExperimentalModoApi
import com.github.terrakok.modo.Modo
import com.github.terrakok.modo.RootScreen
import com.github.terrakok.modo.animation.ScreenTransition
import com.github.terrakok.modo.animation.StackTransitionType
import com.github.terrakok.modo.animation.calculateStackTransitionType
import com.github.terrakok.modo.stack.StackScreen
import com.github.terrakok.modo.stack.StackState
import com.softwareit.sduhub.navigation.MainMultiScreen
import com.softwareit.sduhub.navigation.MainStackScreen
import com.softwareit.sduhub.ui.theme.SduHubTheme

class MainActivity : AppCompatActivity() {

    private var rootScreen: RootScreen<StackScreen>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        rootScreen = Modo.init(savedInstanceState, rootScreen) {
            MainStackScreen(MainMultiScreen())
        }

        setContent {
            SduHubTheme {
                Surface {
                    rootScreen?.Content()
                }
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        Modo.save(outState, rootScreen)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) {
            Modo.onRootScreenFinished(rootScreen)
        }
    }
}

@Composable
@OptIn(ExperimentalAnimationApi::class, ExperimentalModoApi::class)
fun ComposeRendererScope<StackState>.SlideTransition() {
    ScreenTransition(
        transitionSpec = {
            val transitionType = calculateStackTransitionType(oldState, newState)
            when {
                transitionType == StackTransitionType.Replace -> {
                    scaleIn(initialScale = 2f) + fadeIn() togetherWith fadeOut()
                }

                oldState?.stack?.last() is DialogScreen && newState?.stack?.last() is DialogScreen -> {
                    fadeIn() togetherWith fadeOut()
                }

                else -> {
                    val (initialOffset, targetOffset) = when (transitionType) {
                        StackTransitionType.Pop -> ({ size: Int -> -size }) to ({ size: Int -> size })
                        else -> ({ size: Int -> size }) to ({ size: Int -> -size })
                    }
                    slideInHorizontally(initialOffsetX = initialOffset) togetherWith
                            slideOutHorizontally(targetOffsetX = targetOffset)
                }
            }
        }
    )
}