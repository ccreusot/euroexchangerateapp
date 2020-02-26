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

class RateDetailViewModel(
    private val useCase: FetchHistoryForSymbolsUseCase,
    private val code: String
) : ViewModel() {

    val isLoading: LiveData<Boolean> = MutableLiveData<Boolean>(true)

    val rateList: LiveData<List<DateRateViewModel>> by lazy {
        fetchDetails(code)
        MutableLiveData<List<DateRateViewModel>>(emptyList())
    }

    private fun fetchDetails(code: String) {
        viewModelScope.launch {
            var hasThrown = false
            val rates =
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
            (rateList as MutableLiveData).value = rates
            if (!hasThrown) {
                (isLoading as MutableLiveData).value = false
            }
        }
    }
}