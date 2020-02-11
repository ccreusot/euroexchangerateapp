package fr.cedriccreusot.euroexchangerate.rates.screens.viewmodels

import androidx.lifecycle.*
import fr.cedriccreusot.euroexchangerate.rates.usecases.FetchLatestRatesUseCase
import fr.cedriccreusot.euroexchangerate.rates.usecases.models.Rate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RateListViewModel(private val useCase: FetchLatestRatesUseCase) : ViewModel() {
    val isLoading : MutableLiveData<Boolean> = MutableLiveData(true)

    val rateList : MutableLiveData<List<Rate>> = MutableLiveData<List<Rate>>(emptyList())

    fun fetchRates() {
        viewModelScope.launch(Dispatchers.Main) {
            isLoading.value = true
            val rates = withContext(Dispatchers.IO) {
                try {
                    useCase.invoke()
                } catch (e : Exception) {
                    null
                }
            }
            if (rates != null) {
                rateList.value = rates
                isLoading.value = false
            }
        }
    }
}
