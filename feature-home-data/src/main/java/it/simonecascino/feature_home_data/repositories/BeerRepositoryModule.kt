package it.simonecascino.feature_home_data.repositories

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.simonecascino.home_domain.repositories.BeerRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class BeerRepositoryModule {

    @Binds
    abstract fun bindRepository(repositoryImpl: BeerRepositoryImpl): BeerRepository

}