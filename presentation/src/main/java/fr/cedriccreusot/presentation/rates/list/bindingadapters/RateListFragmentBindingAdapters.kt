package fr.cedriccreusot.presentation.rates.list.bindingadapters

import android.widget.ViewFlipper
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import fr.cedriccreusot.presentation.rates.list.adapters.RateListAdapter
import fr.cedriccreusot.presentation.rates.list.viewmodels.RateViewModel

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