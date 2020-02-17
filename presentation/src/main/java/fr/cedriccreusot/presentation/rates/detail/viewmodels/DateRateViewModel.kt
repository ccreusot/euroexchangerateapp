package fr.cedriccreusot.presentation.rates.detail.viewmodels

import fr.cedriccreusot.domain.rates.models.DateRate

data class DateRateViewModel(val dateRate: DateRate) {
    fun print() : String = "${dateRate.date} ${dateRate.rate.ratio} EUR"
}