package it.simonecascino.feature_home_data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import it.simonecascino.feature_home_data.database.daos.BeerDao
import it.simonecascino.feature_home_data.database.entities.BeerDb

@Database(entities = [BeerDb::class],
    version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase(){

    abstract val beerDao: BeerDao

}