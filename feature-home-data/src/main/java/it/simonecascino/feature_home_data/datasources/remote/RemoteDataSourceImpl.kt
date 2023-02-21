package it.simonecascino.feature_home_data.datasources.remote

import it.simonecascino.feature_home_data.api.models.BeerResponse
import it.simonecascino.feature_home_data.api.services.BeerApi
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val beerApi: BeerApi
): RemoteDataSource {
    override suspend fun getBeers(): List<BeerResponse> = beerApi.getBeers()
}