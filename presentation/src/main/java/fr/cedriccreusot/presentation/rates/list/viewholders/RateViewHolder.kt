package fr.cedriccreusot.presentation.rates.list.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import fr.cedriccreusot.presentation.rates.list.viewmodels.RateViewModel
import kotlinx.android.synthetic.main.rate_list_item.view.*

class RateViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item : RateViewModel) {
        with(itemView) {
            labelTextView.text = item.print()
            detailButton.setOnClickListener {
                item.openDetail()
            }
        }
    }
}