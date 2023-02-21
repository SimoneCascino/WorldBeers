package it.simonecascino.worldbeers.ui

import android.net.Uri
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import it.simonecascino.core.ui.components.CustomTopAppBar
import it.simonecascino.core.ui.components.rememberLiftState
import it.simonecascino.destination.Destinations
import it.simonecascino.worldbeers.R

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun App(){

    val navController = rememberAnimatedNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route

    val currentDestination: Destinations = if(currentRoute != null)
        Destinations.fromPath(currentRoute)
    else Destinations.HomeScreen

    val title = when {
        currentDestination == Destinations.HomeScreen -> stringResource(id = R.string.app_name)
        currentDestination.dynamicTitle -> Uri.decode(navBackStackEntry?.arguments?.getString(Destinations.ANDROID_TITLE) ?: "")
        else -> ""
    }

    val liftState = rememberLiftState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopAppBar(
                title = title,
                liftState = liftState,
                isNavigationIconVisible = currentDestination == Destinations.HomeScreen
            ) {
                navController.navigateUp()
            }
        }
    ) {
        AppGraph(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            liftState = liftState,
            navController = navController
        )
    }

}