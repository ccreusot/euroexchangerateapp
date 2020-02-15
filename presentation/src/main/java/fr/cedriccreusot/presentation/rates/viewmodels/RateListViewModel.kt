package fr.cedriccreusot.presentation.rates.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.cedriccreusot.domain.rates.FetchLatestRatesUseCase
import fr.cedriccreusot.domain.rates.models.Rate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RateListViewModel(private val useCase: FetchLatestRatesUseCase) : ViewModel() {
    val isLoading : MutableLiveData<Boolean> = MutableLiveData(true)

    val rateList : MutableLiveData<List<RateViewModel>> = MutableLiveData(emptyList())

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
                rateList.value = rates.map {
                    RateViewModel(it.code, it.ratio)
                }
                isLoading.value = false
            }
        }
    }
}
