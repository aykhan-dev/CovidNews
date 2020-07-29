package ev.aykhan.covid.repository

import android.util.Log
import ev.aykhan.covid.local.AppDatabase
import ev.aykhan.covid.manager.SingletonHolder
import ev.aykhan.covid.network.ApiInitHelper
import ev.aykhan.covid.utils.toNewsList
import timber.log.Timber

class NewsRepository private constructor(database: AppDatabase) {

    companion object : SingletonHolder<NewsRepository, AppDatabase>(::NewsRepository)

    private val tag = NewsRepository::class.java.name

    private val service = ApiInitHelper.newsService
    private val newsDao = database.newsDao

    val listOfNews get() = newsDao.getAll()

    suspend fun getNewsList() {
        try {
            val response = service.getNews()
            if (response.isSuccessful && response.code() == 200) {
                val data = response.body()?.toNewsList() ?: listOf()
                newsDao.cacheNews(data)
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

}