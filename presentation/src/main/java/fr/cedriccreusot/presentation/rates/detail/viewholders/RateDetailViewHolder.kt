package fr.cedriccreusot.presentation.rates.detail.viewholders

import androidx.recyclerview.widget.RecyclerView
import fr.cedriccreusot.presentation.databinding.RateDetailListItemBinding
import fr.cedriccreusot.presentation.rates.detail.viewmodels.DateRateViewModel

class RateDetailViewHolder(private val binding: RateDetailListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: DateRateViewModel) {
        binding.viewModel = item
    }
}