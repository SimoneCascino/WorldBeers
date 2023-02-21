package it.simonecascino.feature_home_data.database.daos

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.simonecascino.feature_home_data.database.AppDatabase

@Module
@InstallIn(SingletonComponent::class)
object BeerDaoModule {

    @Provides
    fun provideDao(database: AppDatabase) = database.beerDao

}