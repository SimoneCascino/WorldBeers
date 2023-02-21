package it.simonecascino.feature_home_data.datasources

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.simonecascino.feature_home_data.datasources.local.LocalDataSource
import it.simonecascino.feature_home_data.datasources.local.LocalDataSourceImpl
import it.simonecascino.feature_home_data.datasources.remote.RemoteDataSource
import it.simonecascino.feature_home_data.datasources.remote.RemoteDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DatasourceModule {

    @Binds
    abstract fun provideLocalDataSource(localDatasourceImpl: LocalDataSourceImpl):
            LocalDataSource

    @Binds
    abstract fun provideRemoteDataSource(remoteDatasourceImpl: RemoteDataSourceImpl):
            RemoteDataSource

}