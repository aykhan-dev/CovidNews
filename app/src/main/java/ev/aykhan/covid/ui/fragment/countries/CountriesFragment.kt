package ev.aykhan.covid.ui.fragment.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import ev.aykhan.covid.databinding.FragmentCountriesBinding
import ev.aykhan.covid.viewModel.fragment.countries.CountriesViewModel
import kotlinx.coroutines.flow.collectLatest

class CountriesFragment : Fragment() {

    private lateinit var binding: FragmentCountriesBinding

    private val viewModel: CountriesViewModel by viewModels()
    private val adapter = CountriesAdapter(null)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountriesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUI()
        configureRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeData()
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchListOfCountries()
    }

    private fun bindUI(): Unit = with(binding) {
        lifecycleOwner = this@CountriesFragment
    }

    private fun configureRecyclerView(): Unit = with(binding) {
        recyclerViewCountries.adapter = adapter
    }

    private fun observeData(): Unit = with(viewModel) {
        lifecycleScope.launchWhenCreated {
            listOfCountries.collectLatest {
                binding.progressLoading.isVisible = it.isEmpty()
                adapter.submitList(it)
            }
        }
    }

}