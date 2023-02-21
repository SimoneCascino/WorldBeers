package it.simonecascino.feature_home_data.api.services

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object BeerApiModule {

    @Provides
    fun bindService(retrofit: Retrofit) = retrofit.create(BeerApi::class.java)

}