package fr.cedriccreusot.presentation.rates.detail.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import fr.cedriccreusot.presentation.databinding.RateDetailListItemBinding
import fr.cedriccreusot.presentation.rates.detail.viewholders.RateDetailViewHolder
import fr.cedriccreusot.presentation.rates.detail.viewmodels.DateRateViewModel

class RateDetailListAdapter : ListAdapter<DateRateViewModel, RateDetailViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateDetailViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RateDetailViewHolder(RateDetailListItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RateDetailViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    internal class DiffCallback : DiffUtil.ItemCallback<DateRateViewModel>() {
        override fun areItemsTheSame(oldItem: DateRateViewModel, newItem: DateRateViewModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: DateRateViewModel, newItem: DateRateViewModel): Boolean {
            return oldItem == newItem
        }
    }
}