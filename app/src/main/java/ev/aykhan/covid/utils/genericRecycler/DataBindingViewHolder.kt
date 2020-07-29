package ev.aykhan.covid.utils.genericRecycler

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import ev.aykhan.covid.BR

class DataBindingViewHolder<T>(
    private val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: T,
        position: Int,
        clickListener: (item: T, position: Int) -> Unit
    ): Unit = with(binding) {
        setVariable(BR.data, item)
        executePendingBindings()
    }

}