package fr.cedriccreusot.presentation.rates.list.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.cedriccreusot.domain.rates.FetchLatestRatesUseCase
import fr.cedriccreusot.presentation.rates.routes.RatesRouter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RateListViewModel(private val useCase: FetchLatestRatesUseCase, private val router: RatesRouter) : ViewModel() {
    val isLoading : MutableLiveData<Boolean> = MutableLiveData(true)

    val rateList : MutableLiveData<List<RateViewModel>> by lazy {
        fetchRates()
        MutableLiveData<List<RateViewModel>>(emptyList())
    }

    private fun fetchRates() {
        viewModelScope.launch {
            isLoading.value = true
            val rates = withContext(Dispatchers.IO) {
                try {
                    useCase.invoke()
                } catch (e : Exception) {
                    null
                }
            }
            if (rates != null) {
                rateList.value = rates.map {
                    RateViewModel(it, router)
                }
                isLoading.value = false
            }
        }
    }
}
