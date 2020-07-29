package ev.aykhan.covid

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import ev.aykhan.covid.work.CountriesWork
import ev.aykhan.covid.work.NewsWork
import ev.aykhan.covid.work.StatisticsWork
import timber.log.Timber
import java.util.concurrent.TimeUnit

class CovidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        scheduleWorks()
    }

    private fun scheduleWorks() {
        val workManager = WorkManager.getInstance(applicationContext)

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()

        val requestCountries = PeriodicWorkRequestBuilder<CountriesWork>(8, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        val requestNews = PeriodicWorkRequestBuilder<NewsWork>(8, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        val requestStatistics = PeriodicWorkRequestBuilder<StatisticsWork>(8, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        workManager.enqueue(requestCountries)
        workManager.enqueue(requestNews)
        workManager.enqueue(requestStatistics)
    }

}