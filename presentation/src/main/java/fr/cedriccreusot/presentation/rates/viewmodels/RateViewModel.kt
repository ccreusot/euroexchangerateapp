package fr.cedriccreusot.presentation.rates.viewmodels

import android.util.Log
import androidx.databinding.Bindable
import java.math.BigDecimal

data class RateViewModel(
    private val code: String,
    private val ratio: BigDecimal
) {
    fun print() : String = "$code : $ratio EUR"

    fun openDetail() {
        Log.d(this.javaClass.simpleName, "Open Details ? Yes !")
    }
}