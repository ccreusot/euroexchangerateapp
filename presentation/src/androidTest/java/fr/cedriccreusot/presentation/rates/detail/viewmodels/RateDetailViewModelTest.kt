package fr.cedriccreusot.presentation.rates.detail.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import fr.cedriccreusot.domain.rates.FetchHistoryForSymbolsUseCase
import fr.cedriccreusot.domain.rates.models.DateRate
import fr.cedriccreusot.domain.rates.models.Rate
import fr.cedriccreusot.presentation.test.utils.CoroutinesTestRule
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import java.util.*


@RunWith(AndroidJUnit4::class)
class RateDetailViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun testFechWhenAnExceptionIsRaised() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val observerRateList = Mockito.mock(Observer::class.java)
        val observerIsLoading = Mockito.mock(Observer::class.java)
        val useCase = Mockito.mock(FetchHistoryForSymbolsUseCase::class.java)
        val viewModel = RateDetailViewModel(useCase)

        viewModel.isLoading.observeForever(observerIsLoading as Observer<Boolean>)
        viewModel.rateList.observeForever(observerRateList as Observer<in List<DateRateViewModel>>)
        given(useCase.invoke("USD")).willThrow(Exception())

        viewModel.fetchDetails("USD")

        verify(observerIsLoading, times(1)).onChanged(true)
        verify(observerRateList, times(2)).onChanged(emptyList())
        verify(useCase).invoke("USD")
    }

    @Test
    fun testFechWhenEmptyCode() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val observerRateList = Mockito.mock(Observer::class.java)
        val observerIsLoading = Mockito.mock(Observer::class.java)
        val useCase = Mockito.mock(FetchHistoryForSymbolsUseCase::class.java)
        val viewModel = RateDetailViewModel(useCase)

        viewModel.isLoading.observeForever(observerIsLoading as Observer<Boolean>)
        viewModel.rateList.observeForever(observerRateList as Observer<in List<DateRateViewModel>>)
        given(useCase.invoke("")).willReturn(emptyList())

        viewModel.fetchDetails("")

        verify(observerIsLoading, times(1)).onChanged(true)
        verify(observerIsLoading, times(1)).onChanged(false)
        verify(observerRateList, times(2)).onChanged(emptyList())
        verify(useCase).invoke("")
    }

    @Test
    fun testFechWhenCodeIsOk() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val observerRateList = Mockito.mock(Observer::class.java)
        val observerIsLoading = Mockito.mock(Observer::class.java)
        val useCase = Mockito.mock(FetchHistoryForSymbolsUseCase::class.java)
        val viewModel = RateDetailViewModel(useCase)
        val expectedList = listOf(
            DateRateViewModel(DateRate("2018-01-01", Rate("USD", 1.2.toBigDecimal())))
        )

        viewModel.isLoading.observeForever(observerIsLoading as Observer<Boolean>)
        viewModel.rateList.observeForever(observerRateList as Observer<in List<DateRateViewModel>>)
        given(useCase.invoke("USD")).willReturn(listOf(DateRate("2018-01-01", Rate("USD", 1.2.toBigDecimal()))))

        viewModel.fetchDetails("USD")

        verify(observerIsLoading, times(1)).onChanged(true)
        verify(observerIsLoading, times(1)).onChanged(false)
        verify(observerRateList, times(1)).onChanged(emptyList())
        verify(observerRateList, times(1)).onChanged(expectedList)
        verify(useCase).invoke("USD")
    }
}