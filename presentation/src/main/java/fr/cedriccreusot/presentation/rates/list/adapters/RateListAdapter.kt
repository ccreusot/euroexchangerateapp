package fr.cedriccreusot.presentation.rates.list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import fr.cedriccreusot.presentation.R
import fr.cedriccreusot.presentation.rates.list.viewholders.RateViewHolder
import fr.cedriccreusot.presentation.rates.list.viewmodels.RateViewModel

class RateListAdapter : ListAdapter<RateViewModel, RateViewHolder>(ItemCallback()) {
    override fun onBindViewHolder(holder: RateViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RateViewHolder(inflater.inflate(R.layout.rate_list_item, parent, false))
    }

    internal class ItemCallback : DiffUtil.ItemCallback<RateViewModel>() {
        override fun areItemsTheSame(oldItem: RateViewModel, newItem: RateViewModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: RateViewModel, newItem: RateViewModel): Boolean {
            return oldItem == newItem
        }
    }
}