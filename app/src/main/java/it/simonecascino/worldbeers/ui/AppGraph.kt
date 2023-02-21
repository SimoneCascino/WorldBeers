package it.simonecascino.worldbeers.ui

import android.net.Uri
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.*
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import it.simonecascino.core.navigation.composableVM
import it.simonecascino.core.ui.components.LiftState
import it.simonecascino.destination.Destinations
import it.simonecascino.feature_home_presentation.detail.DetailScreen
import it.simonecascino.feature_home_presentation.detail.DetailState
import it.simonecascino.feature_home_presentation.detail.DetailViewModel
import it.simonecascino.feature_home_presentation.home.HomeScreen
import it.simonecascino.feature_home_presentation.home.HomeState
import it.simonecascino.feature_home_presentation.home.HomeViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppGraph(
    modifier: Modifier = Modifier,
    liftState: LiftState,
    navController: NavHostController = rememberAnimatedNavController()
){

    AnimatedNavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destinations.HomeScreen.route()
    ){

        composableVM<HomeState, HomeViewModel>(Destinations.HomeScreen.route()){state ->

            val lazyListState = rememberLazyListState()

            val lift by remember{
                derivedStateOf {
                    lazyListState.firstVisibleItemIndex > 0 ||
                            (lazyListState.firstVisibleItemScrollOffset > lazyListState.layoutInfo.beforeContentPadding)
                }
            }

            liftState.setValue(lift)

            HomeScreen(
                beers = state.beers,
                loading = state.loading,
                lazyListState = lazyListState,
                query = state.query,
                onValueChange = {
                    search(it)
                }
            ){ name, id ->
                navController.navigate(Destinations.DetailScreen.buildPath(Uri.encode(name), id.toString()))
            }

        }

        composableVM<DetailState, DetailViewModel>(Destinations.DetailScreen.route()){state ->

            liftState.setValue(false)

            state.beerDetailUI?.let {
                DetailScreen(beer = it)
            }
        }

    }

}