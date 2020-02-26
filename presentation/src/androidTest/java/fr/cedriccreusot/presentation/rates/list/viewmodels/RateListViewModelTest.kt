package fr.cedriccreusot.presentation.rates.list.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nhaarman.mockitokotlin2.*
import fr.cedriccreusot.domain.rates.FetchLatestRatesUseCase
import fr.cedriccreusot.domain.rates.models.Rate
import fr.cedriccreusot.presentation.rates.routes.RatesRouter
import fr.cedriccreusot.presentation.test.utils.CoroutinesTestRule
import kotlinx.coroutines.test.*
import org.junit.Before
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

    private lateinit var observerIsLoading: Observer<Boolean>
    private lateinit var observerRateList: Observer<List<RateViewModel>>
    private lateinit var useCase: FetchLatestRatesUseCase
    private lateinit var router: RatesRouter

    @Before
    fun setUp() {
        observerIsLoading = Mockito.mock(Observer::class.java) as Observer<Boolean>
        observerRateList = Mockito.mock(Observer::class.java) as Observer<List<RateViewModel>>
        useCase = Mockito.mock(FetchLatestRatesUseCase::class.java)
        router = Mockito.mock(RatesRouter::class.java)
    }

    @Test
    fun testFetchWhenAnExceptionIsRaised() = coroutinesTestRule.testDispatcher.runBlockingTest {
        // Given
        given(useCase.invoke()).willThrow(Exception())

        // When
        val viewModel = RateListViewModel(useCase, router)
        viewModel.isLoading.observeForever(observerIsLoading)
        viewModel.rateList.observeForever(observerRateList)

        // Then
        verify(observerIsLoading, times(2)).onChanged(true)
        verify(observerRateList).onChanged(emptyList())
        verify(useCase).invoke()
    }

    @Test
    fun testFetchSucceed() = coroutinesTestRule.testDispatcher.runBlockingTest {
        // Given
        given(useCase.invoke()).willReturn(
            listOf(
                Rate("USD", 1.2.toBigDecimal())
            )
        )

        // When
        val viewModel = RateListViewModel(useCase, router)
        viewModel.isLoading.observeForever(observerIsLoading)
        viewModel.rateList.observeForever(observerRateList)

        // Then
        verify(observerIsLoading, times(2)).onChanged(true)
        verify(observerIsLoading, times(1)).onChanged(false)
        verify(observerRateList).onChanged(emptyList())
        verify(observerRateList).onChanged(listOf(
            RateViewModel(Rate("USD", 1.2.toBigDecimal()), router)
        ))
        verify(useCase).invoke()
    }

}
