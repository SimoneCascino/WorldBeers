package it.simonecascino.core.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.*
import it.simonecascino.core.architecture.BaseState
import it.simonecascino.core.architecture.BaseViewModel
import it.simonecascino.core.ui.animations.slideInPopTransition
import it.simonecascino.core.ui.animations.slideInTransition
import it.simonecascino.core.ui.animations.slideOutPopTransition
import it.simonecascino.core.ui.animations.slideOutTransition

@OptIn(ExperimentalAnimationApi::class)
inline fun <S: BaseState, reified V: BaseViewModel<S>> NavGraphBuilder.composableVM(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    crossinline content: @Composable V.(S) -> Unit
) {

    composable(
        route = route,
        arguments = arguments,
        deepLinks = deepLinks,
        enterTransition = {
            slideInTransition
        },
        exitTransition = {
            slideOutTransition
        },
        popEnterTransition = {
            slideInPopTransition
        },
        popExitTransition = {
            slideOutPopTransition
        }
    ){
        val viewModel = hiltViewModel<V>(it)
        val state by viewModel.currentState.collectAsState()
        viewModel.content(state)
    }
}