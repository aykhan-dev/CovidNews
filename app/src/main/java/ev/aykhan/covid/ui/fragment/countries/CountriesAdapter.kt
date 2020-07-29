package ev.aykhan.covid.ui.fragment.countries

import androidx.recyclerview.widget.DiffUtil
import ev.aykhan.covid.R
import ev.aykhan.covid.model.entity.Country
import ev.aykhan.covid.utils.genericRecycler.DataBindingRecyclerAdapter

class CountriesAdapter(
    clickListener: ((item: Country, position: Int) -> Unit)? = null
) : DataBindingRecyclerAdapter<Country>(DiffCallback(), clickListener) {

    class DiffCallback : DiffUtil.ItemCallback<Country>() {

        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.country == newItem.country
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }

    }

    override fun getItemViewType(position: Int): Int = R.layout.item_country

}