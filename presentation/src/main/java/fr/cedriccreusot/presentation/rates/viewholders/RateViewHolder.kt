package fr.cedriccreusot.presentation.rates.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import fr.cedriccreusot.presentation.databinding.RateListItemBinding
import fr.cedriccreusot.presentation.rates.viewmodels.RateViewModel

class RateViewHolder(private val binding: RateListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item : RateViewModel) {
        binding.viewModel = item
    }
}