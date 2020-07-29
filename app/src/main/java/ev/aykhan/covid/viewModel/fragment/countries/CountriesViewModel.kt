package ev.aykhan.covid.viewModel.fragment.countries

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import ev.aykhan.covid.local.getDatabase
import ev.aykhan.covid.repository.CountriesRepository
import kotlinx.coroutines.launch

class CountriesViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application.applicationContext

    private val countriesRepository = CountriesRepository.getInstance(getDatabase(context))

    val listOfCountries = countriesRepository.listOfCountries

    fun fetchListOfCountries() {
        viewModelScope.launch { countriesRepository.getCountriesList() }
    }

}