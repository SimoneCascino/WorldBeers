package it.simonecascino.feature_home_data.datasources.remote

import it.simonecascino.feature_home_data.api.models.BeerResponse

interface RemoteDataSource {

    suspend fun getBeers(): List<BeerResponse>

}