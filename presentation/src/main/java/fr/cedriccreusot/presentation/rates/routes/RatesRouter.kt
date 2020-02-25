package fr.cedriccreusot.presentation.rates.routes

import androidx.navigation.NavController
import fr.cedriccreusot.presentation.rates.list.RateListFragmentDirections

interface RatesRouter {

    fun getToDetails(code: String)

    companion object {
        fun createRoutes(navController: NavController) : RatesRouter = RateDetailsRouter(navController)
    }
}

private class RateDetailsRouter(private val navController: NavController) : RatesRouter {

    override fun getToDetails(code: String) {
        RateListFragmentDirections.actionRateListFragmentToRateDetailFragment(code).also { direction ->
            navController.navigate(direction)
        }
    }
}

