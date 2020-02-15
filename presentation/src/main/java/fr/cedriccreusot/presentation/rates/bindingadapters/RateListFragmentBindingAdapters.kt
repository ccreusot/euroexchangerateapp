package fr.cedriccreusot.presentation.rates.bindingadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fr.cedriccreusot.presentation.rates.viewholders.RateViewHolder
import fr.cedriccreusot.domain.rates.models.Rate
import fr.cedriccreusot.presentation.rates.adapters.RateListAdapter
import fr.cedriccreusot.presentation.rates.viewmodels.RateViewModel

@BindingAdapter("rates:isLoading")
fun isLoading(view: ViewFlipper, isLoading : Boolean) {
    view.displayedChild = if (isLoading) 0 else 1
}

@BindingAdapter("rates:list")
fun rates(recyclerView: RecyclerView, rates : List<RateViewModel>) {
    recyclerView.adapter = RateListAdapter().apply {
        submitList(rates)
    }
}