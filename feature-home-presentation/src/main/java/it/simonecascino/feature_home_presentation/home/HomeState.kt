package it.simonecascino.feature_home_presentation.home

import it.simonecascino.core.architecture.BaseState
import it.simonecascino.feature_home_presentation.home.models.BeerHomeUI

data class HomeState(
    val query: String = "",
    val loading: Boolean = false,
    val beers: List<BeerHomeUI> = emptyList()
): BaseState