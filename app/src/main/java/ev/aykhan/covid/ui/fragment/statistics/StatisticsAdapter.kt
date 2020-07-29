package ev.aykhan.covid.ui.fragment.statistics

import androidx.recyclerview.widget.DiffUtil
import ev.aykhan.covid.R
import ev.aykhan.covid.model.entity.Statistics
import ev.aykhan.covid.utils.genericRecycler.DataBindingRecyclerAdapter

class StatisticsAdapter(
    clickListener: (item: Statistics, position: Int) -> Unit
) : DataBindingRecyclerAdapter<Statistics>(DiffCallback(), clickListener) {

    class DiffCallback : DiffUtil.ItemCallback<Statistics>() {

        override fun areItemsTheSame(oldItem: Statistics, newItem: Statistics): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Statistics, newItem: Statistics): Boolean {
            return oldItem == newItem
        }

    }

    override fun getItemViewType(position: Int): Int = R.layout.item_statistics

}