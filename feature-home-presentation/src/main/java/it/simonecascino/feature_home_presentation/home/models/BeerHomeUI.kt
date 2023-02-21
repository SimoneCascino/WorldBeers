package it.simonecascino.feature_home_presentation.home.models

import it.simonecascino.home_domain.models.BeerDomain

data class BeerHomeUI(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val description: String,
    val abv: Float,
    val ibu: Float?
){

    companion object{

        fun fromDomainModel(
            beerDomain: BeerDomain
        ) = BeerHomeUI(
            id = beerDomain.id,
            name = beerDomain.name,
            imageUrl = beerDomain.imageUrl,
            description = beerDomain.description,
            abv = beerDomain.abv,
            ibu = beerDomain.ibu
        )

    }

}

