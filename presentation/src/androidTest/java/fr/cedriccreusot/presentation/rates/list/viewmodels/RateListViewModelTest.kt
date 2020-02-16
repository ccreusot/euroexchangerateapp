package fr.cedriccreusot.presentation.rates.list.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nhaarman.mockitokotlin2.*
import fr.cedriccreusot.domain.rates.FetchLatestRatesUseCase
import fr.cedriccreusot.domain.rates.models.Rate
import fr.cedriccreusot.presentation.test.utils.CoroutinesTestRule
import kotlinx.coroutines.test.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class RateListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun testFetchWhenAnExceptionIsRaised() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val observerIsLoading = Mockito.mock(Observer::class.java)
        val observerRateList = Mockito.mock(Observer::class.java)
        val useCase = Mockito.mock(FetchLatestRatesUseCase::class.java)
        val viewModel = RateListViewModel(useCase)

        viewModel.isLoading.observeForever(observerIsLoading as Observer<Boolean>)
        viewModel.rateList.observeForever(observerRateList as Observer<in List<RateViewModel>>)
        given(useCase.invoke()).willThrow(Exception())

        viewModel.fetchRates()

        verify(observerIsLoading, times(2)).onChanged(true)
        verify(observerRateList).onChanged(emptyList())
        verify(useCase).invoke()
    }

    @Test
    fun testFetchSucceed() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val observerIsLoading = Mockito.mock(Observer::class.java)
        val observerRateList = Mockito.mock(Observer::class.java)
        val useCase = Mockito.mock(FetchLatestRatesUseCase::class.java)
        val viewModel = RateListViewModel(useCase)

        viewModel.isLoading.observeForever(observerIsLoading as Observer<Boolean>)
        viewModel.rateList.observeForever(observerRateList as Observer<in List<RateViewModel>>)

        given(useCase.invoke()).willReturn(
            listOf(
                Rate("USD", 1.2.toBigDecimal())
            )
        )

        viewModel.fetchRates()

        verify(observerIsLoading, times(2)).onChanged(true)
        verify(observerIsLoading, times(1)).onChanged(false)
        verify(observerRateList).onChanged(emptyList())
        verify(observerRateList).onChanged(listOf(
            RateViewModel(Rate("USD", 1.2.toBigDecimal()))
        ))
        verify(useCase).invoke()
    }

}
