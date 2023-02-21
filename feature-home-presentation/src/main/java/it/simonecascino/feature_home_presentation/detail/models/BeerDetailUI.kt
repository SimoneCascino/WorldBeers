package it.simonecascino.feature_home_presentation.detail.models

import it.simonecascino.home_domain.models.BeerDomain

data class BeerDetailUI(
    val firstBrewed: String,
    val foodPairing: String,
    val brewerTips: String
){

    companion object{
        fun fromDomain(beerDomain: BeerDomain) = BeerDetailUI(
            firstBrewed = beerDomain.firstBrewed,
            foodPairing = beerDomain.foodPairing,
            brewerTips = beerDomain.brewersTips
        )
    }

}