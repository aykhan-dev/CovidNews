package ev.aykhan.covid.viewModel.fragment.statistics

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import ev.aykhan.covid.local.getDatabase
import ev.aykhan.covid.repository.StatisticsRepository
import kotlinx.coroutines.launch

class StatisticsViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application.applicationContext

    private val statisticsRepository = StatisticsRepository.getInstance(getDatabase(context))

    val listOfStatistics = statisticsRepository.statistics

    fun fetchStatistics() {
        viewModelScope.launch { statisticsRepository.getStatistics() }
    }

}