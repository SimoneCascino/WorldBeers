package it.simonecascino.feature_home_data.datasources.local

import it.simonecascino.feature_home_data.database.daos.BeerDao
import it.simonecascino.feature_home_data.database.entities.BeerDb
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val beerDao: BeerDao
): LocalDataSource {

    override suspend fun saveBeers(beers: List<BeerDb>) {
        beerDao.saveBeers(beers)
    }

    override fun getBeers(): Flow<List<BeerDb>> = beerDao.getBeers()

    override fun getBeersByQuery(query: String): Flow<List<BeerDb>> = beerDao.getBeersByQuery(query)

    override suspend fun getBeer(id: Int): BeerDb = beerDao.getBeer(id)

}