package ev.aykhan.covid.repository

import ev.aykhan.covid.local.AppDatabase
import ev.aykhan.covid.manager.SingletonHolder
import ev.aykhan.covid.network.ApiInitHelper
import ev.aykhan.covid.utils.toCountryList
import timber.log.Timber

class CountriesRepository private constructor(database: AppDatabase) {

    companion object : SingletonHolder<CountriesRepository, AppDatabase>(::CountriesRepository)

    private val tag = CountriesRepository::class.java.name

    private val service = ApiInitHelper.countriesService
    private val countriesDao = database.countriesDao

    val listOfCountries get() = countriesDao.getAll()

    suspend fun getCountriesList() {
        try {
            val response = service.getCountries()
            if (response.isSuccessful && response.code() == 200) {
                val data = response.body()?.toCountryList() ?: listOf()
                if (data.isNotEmpty()) countriesDao.cacheCountries(data)
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

}