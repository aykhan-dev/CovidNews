package ev.aykhan.covid.ui.fragment.news

import androidx.recyclerview.widget.DiffUtil
import ev.aykhan.covid.R
import ev.aykhan.covid.model.entity.News
import ev.aykhan.covid.utils.genericRecycler.DataBindingRecyclerAdapter

class NewsAdapter(
    clickListener: (item: News, position: Int) -> Unit
) : DataBindingRecyclerAdapter<News>(DiffCallback(), clickListener) {

    class DiffCallback : DiffUtil.ItemCallback<News>() {

        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }

    }

    override fun getItemViewType(position: Int): Int = R.layout.item_news

}