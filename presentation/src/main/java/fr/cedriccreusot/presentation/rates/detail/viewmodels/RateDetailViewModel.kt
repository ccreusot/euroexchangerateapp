package fr.cedriccreusot.presentation.rates.detail.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.cedriccreusot.domain.rates.FetchHistoryForSymbolsUseCase
import fr.cedriccreusot.domain.rates.models.DateRate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RateDetailViewModel(private val useCase: FetchHistoryForSymbolsUseCase) : ViewModel() {

    val isLoading: LiveData<Boolean> = MutableLiveData<Boolean>(true)
    val rateList: LiveData<List<DateRateViewModel>> = MutableLiveData<List<DateRateViewModel>>(emptyList())

    fun fetchDetails(code: String) {
        viewModelScope.launch {
            var hasThrown = false
            (rateList as MutableLiveData).value =
                withContext(Dispatchers.IO) {
                    try {
                        useCase.invoke(code).map {
                            DateRateViewModel(it)
                        }
                    } catch (e: Exception) {
                        hasThrown = true
                        emptyList<DateRateViewModel>()
                    }
                }
            if (!hasThrown) {
                (isLoading as MutableLiveData).value = false
            }
        }
    }
}