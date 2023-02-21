package it.simonecascino.feature_home_presentation.detail

import it.simonecascino.core.architecture.BaseState
import it.simonecascino.feature_home_presentation.detail.models.BeerDetailUI

data class DetailState(
    val beerDetailUI: BeerDetailUI? = null
): BaseState