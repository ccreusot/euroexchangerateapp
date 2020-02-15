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

@BindingAdapter("rateIsLoading")
fun isLoading(view: ViewFlipper, isLoading : Boolean) {
    view.displayedChild = if (isLoading) 0 else 1
}

@BindingAdapter("rates")
fun rates(recyclerView: RecyclerView, rates : List<Rate>) {
    recyclerView.adapter = object : ListAdapter<Rate, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<Rate>(){
        override fun areItemsTheSame(oldItem: Rate, newItem: Rate): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Rate, newItem: Rate): Boolean {
            return oldItem == newItem
        }
    }) {
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            holder.itemView.findViewById<TextView>(android.R.id.text1).text = getItem(position).code
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false)
            return RateViewHolder(view)
        }
    }.apply {
        submitList(rates)
    }
}