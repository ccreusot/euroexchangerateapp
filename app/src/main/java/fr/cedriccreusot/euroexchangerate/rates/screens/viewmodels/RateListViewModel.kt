package fr.cedriccreusot.euroexchangerate.rates.screens.viewmodels

import androidx.lifecycle.ViewModel
import fr.cedriccreusot.euroexchangerate.rates.usecases.FetchLatestRatesUseCase

class RateListViewModel(private val useCase: FetchLatestRatesUseCase) : ViewModel() {
    val text: String = "Hello the world !"
}
