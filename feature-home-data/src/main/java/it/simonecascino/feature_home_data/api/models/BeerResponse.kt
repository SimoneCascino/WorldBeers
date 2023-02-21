package it.simonecascino.feature_home_data.api.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import it.simonecascino.feature_home_data.database.entities.BeerDb

@JsonClass(generateAdapter = true)
data class BeerResponse(
    val id: Int,
    val name: String,
    @Json(name = "first_brewed") val firstBrewed: String,
    val description: String,
    @Json(name = "image_url") val imageUrl: String,
    val abv: Float,
    val ibu: Float?,
    @Json(name = "food_pairing") val foodPairing: List<String>,
    @Json(name = "brewers_tips") val brewersTips: String
){

    fun toDbModel(): BeerDb = BeerDb(
        id = id,
        name = name,
        firstBrewed = firstBrewed,
        description = description,
        imageUrl = imageUrl,
        abv = abv,
        ibu = ibu,
        foodPairing = foodPairing.joinToString("\n"),
        brewersTips = brewersTips
    )

}

fun List<BeerResponse>.toDbModel(): List<BeerDb> = map { it.toDbModel() }