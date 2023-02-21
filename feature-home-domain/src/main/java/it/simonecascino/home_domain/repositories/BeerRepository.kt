package it.simonecascino.home_domain.repositories

import it.simonecascino.home_domain.models.BeerDomain
import it.simonecascino.home_domain.utils.ApiStatus
import kotlinx.coroutines.flow.Flow

interface BeerRepository {

    fun getBeers(): Flow<List<BeerDomain>>

    fun getBeersByQuery(query: String): Flow<List<BeerDomain>>

    fun downloadBeers(): Flow<ApiStatus>

    suspend fun getBeer(id: Int): BeerDomain

}