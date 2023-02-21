package it.simonecascino.home_domain.models

data class BeerDomain(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val firstBrewed: String,
    val abv: Float,
    val ibu: Float?,
    val foodPairing: String,
    val brewersTips: String
)