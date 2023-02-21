package it.simonecascino.worldbeers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import it.simonecascino.core.ui.theme.WorldBeersTheme
import it.simonecascino.worldbeers.ui.App

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            WorldBeersTheme {
                App()
            }
        }
    }
}