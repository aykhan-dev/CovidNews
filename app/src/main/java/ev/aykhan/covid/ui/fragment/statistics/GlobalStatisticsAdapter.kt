package ev.aykhan.covid.ui.fragment.statistics

import androidx.recyclerview.widget.DiffUtil
import ev.aykhan.covid.R
import ev.aykhan.covid.model.entity.GlobalStatistics
import ev.aykhan.covid.utils.genericRecycler.DataBindingRecyclerAdapter

class GlobalStatisticsAdapter(
    clickListener: ((item: GlobalStatistics, position: Int) -> Unit)? = null
) : DataBindingRecyclerAdapter<GlobalStatistics>(DiffCallback(), clickListener) {

    class DiffCallback : DiffUtil.ItemCallback<GlobalStatistics>() {

        override fun areItemsTheSame(
            oldItem: GlobalStatistics,
            newItem: GlobalStatistics
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: GlobalStatistics,
            newItem: GlobalStatistics
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun getItemViewType(position: Int): Int = R.layout.item_statistics

}