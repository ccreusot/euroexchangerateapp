package fr.cedriccreusot.presentation.rates.list.viewmodels

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import fr.cedriccreusot.domain.rates.models.Rate
import fr.cedriccreusot.presentation.R
import fr.cedriccreusot.presentation.rates.detail.RateDetailFragment
import fr.cedriccreusot.presentation.rates.detail.RateDetailFragmentArgs
import fr.cedriccreusot.presentation.rates.routes.RatesRouter
import java.math.BigDecimal

data class RateViewModel(
    private val rate: Rate,
    private val router: RatesRouter
) {
    fun print() : String = "${rate.code} : ${rate.ratio} EUR"

    fun openDetail() {
        router.getToDetails(rate.code)
    }
}
