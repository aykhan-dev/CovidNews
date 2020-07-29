package ev.aykhan.covid.ui.fragment.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import ev.aykhan.covid.databinding.FragmentNewsBinding
import ev.aykhan.covid.utils.toParcelable
import ev.aykhan.covid.viewModel.fragment.news.NewsViewModel
import kotlinx.coroutines.flow.collectLatest

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding

    private val viewModel: NewsViewModel by viewModels()
    private val navController by lazy { findNavController() }

    private val adapter = NewsAdapter { item, _ ->
        val data = item.toParcelable()
        navController.navigate(NewsFragmentDirections.actionNewsFragmentToDetailedNewsFragment(data))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(inflater)
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
        viewModel.fetchNewsList()
    }

    private fun bindUI(): Unit = with(binding) {
        lifecycleOwner = this@NewsFragment
    }

    private fun configureRecyclerView(): Unit = with(binding) {
        recyclerViewNews.adapter = adapter
    }

    private fun observeData(): Unit = with(viewModel) {
        lifecycleScope.launchWhenCreated {
            listOfNews.collectLatest {
                binding.progressLoading.isVisible = it.isEmpty()
                adapter.submitList(it)
            }
        }
    }

}