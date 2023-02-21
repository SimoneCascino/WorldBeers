package it.simonecascino.feature_home_data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import it.simonecascino.feature_home_data.database.entities.BeerDb
import kotlinx.coroutines.flow.Flow

@Dao
interface BeerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBeers(beers: List<BeerDb>)

    @Query("select * from beers")
    fun getBeers(): Flow<List<BeerDb>>

    @Query("select * from beers where name like '%'||:query||'%' or description like '%'||:query||'%'")
    fun getBeersByQuery(query: String): Flow<List<BeerDb>>

    @Query("select * from beers where id = :id")
    suspend fun getBeer(id: Int): BeerDb

}