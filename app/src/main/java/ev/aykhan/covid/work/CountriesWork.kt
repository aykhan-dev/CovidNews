package ev.aykhan.covid.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import ev.aykhan.covid.local.getDatabase
import ev.aykhan.covid.repository.CountriesRepository

class CountriesWork(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val countriesRepository = CountriesRepository.getInstance(database)
        return try {
            countriesRepository.getCountriesList()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

}