package ev.aykhan.covid.repository

import ev.aykhan.covid.local.AppDatabase
import ev.aykhan.covid.manager.SingletonHolder
import ev.aykhan.covid.network.ApiInitHelper
import ev.aykhan.covid.utils.toNewsList

class NewsRepository private constructor(database: AppDatabase) {

    companion object : SingletonHolder<NewsRepository, AppDatabase>(::NewsRepository)

    private val service = ApiInitHelper.newsService
    private val newsDao = database.newsDao

    val listOfNews get() = newsDao.getAll()

    suspend fun getNewsList() {
        val response = service.getNews()
        if (response.isSuccessful && response.code() == 200) {
            val data = response.body()?.toNewsList() ?: listOf()
            newsDao.cacheNews(data)
        }
    }

}