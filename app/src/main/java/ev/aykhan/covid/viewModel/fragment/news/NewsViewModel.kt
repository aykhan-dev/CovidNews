package ev.aykhan.covid.viewModel.fragment.news

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import ev.aykhan.covid.local.getDatabase
import ev.aykhan.covid.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application.applicationContext

    private val newsRepository = NewsRepository.getInstance(getDatabase(context))

    val listOfNews = newsRepository.listOfNews

    fun fetchNewsList() {
        viewModelScope.launch { newsRepository.getNewsList() }
    }

}