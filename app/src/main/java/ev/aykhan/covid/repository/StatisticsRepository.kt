package ev.aykhan.covid.repository

import ev.aykhan.covid.local.AppDatabase
import ev.aykhan.covid.manager.SingletonHolder
import ev.aykhan.covid.network.ApiInitHelper
import ev.aykhan.covid.utils.toStatisticsList
import timber.log.Timber

class StatisticsRepository private constructor(database: AppDatabase) {

    companion object : SingletonHolder<StatisticsRepository, AppDatabase>(::StatisticsRepository)

    private val service = ApiInitHelper.statisticsService
    private val statisticsDao = database.statisticsDao

    val globalStatistics get() = statisticsDao.getAllGlobalStatistics()

    suspend fun getGlobalStatistics(titles: Array<String>) {
        try {
            val response = service.getGlobalStatistics()
            if (response.isSuccessful && response.code() == 200) {
                val data = response.body()?.toStatisticsList(titles) ?: listOf()
                statisticsDao.cacheGlobalStatistics(data)
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

}