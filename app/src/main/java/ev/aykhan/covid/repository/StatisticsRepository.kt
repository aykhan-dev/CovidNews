package ev.aykhan.covid.repository

import ev.aykhan.covid.local.AppDatabase
import ev.aykhan.covid.manager.SingletonHolder
import ev.aykhan.covid.network.ApiInitHelper
import ev.aykhan.covid.utils.toStatisticsList

class StatisticsRepository private constructor(database: AppDatabase) {

    companion object : SingletonHolder<StatisticsRepository, AppDatabase>(::StatisticsRepository)

    private val service = ApiInitHelper.statisticsService
    private val statisticsDao = database.statisticsDao

    val statistics get() = statisticsDao.getAll()

    suspend fun getStatistics() {
        val response = service.getGlobalStatistics()
        if (response.isSuccessful && response.code() == 200) {
            val data = response.body()?.toStatisticsList() ?: listOf()
            statisticsDao.cacheStatistics(data)
        }
    }

}