package it.simonecascino.core.ui.animations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

@ExperimentalAnimationApi
val slideInTransition = slideInHorizontally(
    initialOffsetX = { it },
    animationSpec = defaultTransitionSpec()
)

@ExperimentalAnimationApi
val slideOutTransition = slideOutHorizontally(
    targetOffsetX = { -it },
    animationSpec = defaultTransitionSpec()
)

@ExperimentalAnimationApi
val slideInPopTransition = slideInHorizontally(
    initialOffsetX = { -it },
    animationSpec = defaultTransitionSpec()
)

@ExperimentalAnimationApi
val slideOutPopTransition = slideOutHorizontally(
    targetOffsetX = { it },
    animationSpec = defaultTransitionSpec()
)