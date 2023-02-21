package it.simonecascino.feature_home_data.repositories

import it.simonecascino.feature_home_data.api.models.toDbModel
import it.simonecascino.feature_home_data.database.entities.toDomainModel
import it.simonecascino.feature_home_data.datasources.local.LocalDataSource
import it.simonecascino.feature_home_data.datasources.remote.RemoteDataSource
import it.simonecascino.home_domain.models.BeerDomain
import it.simonecascino.home_domain.repositories.BeerRepository
import it.simonecascino.home_domain.utils.ApiStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BeerRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): BeerRepository {

    override fun downloadBeers(): Flow<ApiStatus> = flow{

        emit(ApiStatus.Loading)

        localDataSource.saveBeers(
            remoteDataSource.getBeers().toDbModel()
        )

        emit(ApiStatus.Idle)

    }.catch {
        emit(ApiStatus.Fail(it))
    }

    override suspend fun getBeer(id: Int): BeerDomain = localDataSource.getBeer(id).toDomainModel()

    override fun getBeers(): Flow<List<BeerDomain>> = localDataSource.getBeers().map {
        it.toDomainModel()
    }

    override fun getBeersByQuery(query: String) = localDataSource.getBeersByQuery(query).map {
        it.toDomainModel()
    }

}