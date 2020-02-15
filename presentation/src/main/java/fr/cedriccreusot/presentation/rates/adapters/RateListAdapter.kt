package fr.cedriccreusot.presentation.rates.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fr.cedriccreusot.domain.rates.models.Rate
import fr.cedriccreusot.presentation.databinding.RateListItemBinding
import fr.cedriccreusot.presentation.rates.viewholders.RateViewHolder
import fr.cedriccreusot.presentation.rates.viewmodels.RateViewModel

class RateListAdapter : ListAdapter<RateViewModel, RateViewHolder>(ItemCallback()) {
    override fun onBindViewHolder(holder: RateViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateViewHolder {
        return RateViewHolder(RateListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
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