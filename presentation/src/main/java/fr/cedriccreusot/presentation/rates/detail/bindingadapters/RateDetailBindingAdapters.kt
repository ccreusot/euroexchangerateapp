package fr.cedriccreusot.presentation.rates.detail.bindingadapters

import android.graphics.Color
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import fr.cedriccreusot.domain.rates.models.DateRate
import fr.cedriccreusot.presentation.rates.detail.adapters.RateDetailListAdapter
import fr.cedriccreusot.presentation.rates.detail.viewmodels.DateRateViewModel

@BindingAdapter("detail:chartList")
fun addRates(chart: BarChart, rates: List<DateRateViewModel>) {
    chart.setDrawBarShadow(false)
    chart.setDrawValueAboveBar(true)
    chart.setMaxVisibleValueCount(1000)
    chart.setPinchZoom(true)
    chart.setDrawGridBackground(true)
    chart.setGridBackgroundColor(Color.LTGRAY)
    chart.description = Description().apply {
        text = ""
    }
    val barSet = BarDataSet(rates.mapIndexed { index: Int, dateRateViewModel: DateRateViewModel ->
        if (index % 10 == 0) {
            BarEntry(
                index.toFloat(), dateRateViewModel.dateRate.rate.ratio.toFloat()
            )
        } else {
            null
        }
    }.filterNotNull(), "Rate")
    val data = BarData(barSet)
    data.barWidth = 10f
    chart.data = data
    chart.notifyDataSetChanged()
}

@BindingAdapter("detail:rateList")
fun addRates(list: RecyclerView, rates: List<DateRateViewModel>) {
    list.adapter = RateDetailListAdapter().apply { submitList(rates) }
}
