package ev.aykhan.covid.ui.fragment.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import ev.aykhan.covid.databinding.FragmentStatisticsBinding
import ev.aykhan.covid.viewModel.fragment.statistics.StatisticsViewModel
import kotlinx.coroutines.flow.collectLatest

class StatisticsFragment : Fragment() {

    private lateinit var binding: FragmentStatisticsBinding

    private val viewModel: StatisticsViewModel by viewModels()
    private val adapter = StatisticsAdapter { _, _ -> }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStatisticsBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchStatistics()
    }

    private fun configureRecyclerView(): Unit = with(binding) {
        recyclerViewStatistics.adapter = adapter
    }

    private fun observeData(): Unit = with(viewModel) {
        lifecycleScope.launchWhenCreated {
            listOfStatistics.collectLatest {
                binding.progressLoading.isVisible = it.isEmpty()
                adapter.submitList(it)
            }
        }
    }

}