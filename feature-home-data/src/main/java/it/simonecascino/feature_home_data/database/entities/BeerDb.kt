package it.simonecascino.feature_home_data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import it.simonecascino.home_domain.models.BeerDomain

@Entity(
    tableName = "beers"
)
data class BeerDb(
    @PrimaryKey val id: Int,
    val name: String,
    @ColumnInfo(name = "first_brewed") val firstBrewed: String,
    val description: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    val abv: Float,
    val ibu: Float?,
    val foodPairing: String,
    @ColumnInfo(name = "brewers_tips") val brewersTips: String
){

    fun toDomainModel() = BeerDomain(
        id = id,
        name = name,
        firstBrewed = firstBrewed,
        description = description,
        imageUrl = imageUrl,
        abv = abv,
        ibu = ibu,
        foodPairing = foodPairing,
        brewersTips = brewersTips
    )

}

fun List<BeerDb>.toDomainModel() = map { it.toDomainModel() }