package it.simonecascino.feature_home_data.datasources.local

import it.simonecascino.feature_home_data.database.entities.BeerDb
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun saveBeers(beers: List<BeerDb>)

    fun getBeers(): Flow<List<BeerDb>>

    fun getBeersByQuery(query: String): Flow<List<BeerDb>>

    suspend fun getBeer(id: Int): BeerDb

}