package it.simonecascino.core.ui.components

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import it.simonecascino.core.ui.animations.defaultTransitionSpec
import it.simonecascino.core.ui.theme.innerSpacing

@Composable
fun CustomTopAppBar(
    modifier: Modifier = Modifier,
    liftState: LiftState = rememberLiftState(),
    title: String,
    isNavigationIconVisible: Boolean,
    onIconClick: () -> Unit
){

    val elevation by animateDpAsState(targetValue = if(liftState.lifted) AppBarDefaults.TopAppBarElevation else 0.dp)

    val transition = updateTransition(targetState = isNavigationIconVisible, label = "")

    val iconSize by transition.animateDp(
        transitionSpec = {
            defaultTransitionSpec()
        }, label = ""
    ) {
        if(it)
            0.dp
        else 24.dp
    }

    val iconRotation by transition.animateFloat(
        transitionSpec = {
            defaultTransitionSpec()
        }, label = ""
    ){
        if(it)
            180F
        else 0F
    }

    val iconAlpha by transition.animateFloat(
        transitionSpec = {
            defaultTransitionSpec()
        }, label = ""
    ){
        if(it)
            0F
        else 1F
    }

    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        elevation = elevation,
        backgroundColor = MaterialTheme.colors.surface
    ) {

        Icon(
            modifier = Modifier
                .padding(horizontal = innerSpacing)
                .size(iconSize)
                .rotate(iconRotation)
                .alpha(iconAlpha)
                .clickable {
                    onIconClick()
                },
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            tint = MaterialTheme.colors.onSurface
        )

        Text(
            modifier = Modifier.fillMaxWidth().padding(horizontal = innerSpacing),
            text = title,
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.h6
        )



    }

}

class LiftState {

    internal var lifted by mutableStateOf(false)

    fun setValue(shouldLift: Boolean){
        lifted = shouldLift
    }

}

@Composable
fun rememberLiftState() = remember{
    LiftState()
}