package ev.aykhan.covid.local

import android.content.Context
import androidx.room.*
import ev.aykhan.covid.model.entity.Country
import ev.aykhan.covid.model.entity.News
import ev.aykhan.covid.model.entity.Statistics
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CountriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(items: List<Country>)

    @Query("select * from countries order by country asc")
    abstract fun getAll(): Flow<List<Country>>

    @Query("delete from countries")
    abstract fun deleteAll()

    @Transaction
    open suspend fun cacheCountries(items: List<Country>) {
        deleteAll()
        insertAll(items)
    }

}

@Dao
abstract class NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(items: List<News>)

    @Query("select * from news")
    abstract fun getAll(): Flow<List<News>>

    @Query("delete from news")
    abstract fun deleteAll()

    @Transaction
    open suspend fun cacheNews(items: List<News>) {
        deleteAll()
        insertAll(items)
    }

}

@Dao
abstract class StatisticsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(items: List<Statistics>)

    @Query("select * from statistics")
    abstract fun getAll(): Flow<List<Statistics>>

    @Query("delete from statistics")
    abstract fun deleteAll()

    @Transaction
    open suspend fun cacheStatistics(items: List<Statistics>) {
        deleteAll()
        insertAll(items)
    }

}

@Database(
    entities = [
        Country::class,
        News::class,
        Statistics::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract val countriesDao: CountriesDao
    abstract val newsDao: NewsDao
    abstract val statisticsDao: StatisticsDao
}

private const val DATABASE_NAME = "CovidNews.db"
private lateinit var INSTANCE: AppDatabase

fun getDatabase(context: Context): AppDatabase {
    synchronized(AppDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
    return INSTANCE
}