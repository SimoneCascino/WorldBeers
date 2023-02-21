package it.simonecascino.feature_home_data.api.services

import it.simonecascino.feature_home_data.api.models.BeerResponse
import retrofit2.http.GET

interface BeerApi {

    @GET("beers?per_page=80")
    suspend fun getBeers(): List<BeerResponse>

}