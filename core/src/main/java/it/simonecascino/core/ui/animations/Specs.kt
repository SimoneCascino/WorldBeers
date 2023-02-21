package it.simonecascino.core.ui.animations

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween

fun <T>defaultTransitionSpec() = tween<T>(
    durationMillis = 500,
    easing = {
        OvershootInterpolator().getInterpolation(it)
    }
)