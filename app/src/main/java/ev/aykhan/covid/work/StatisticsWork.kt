package ev.aykhan.covid.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import ev.aykhan.covid.local.getDatabase
import ev.aykhan.covid.repository.StatisticsRepository

class StatisticsWork(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val statisticsRepository = StatisticsRepository.getInstance(database)
        return try {
            statisticsRepository.getStatistics()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

}