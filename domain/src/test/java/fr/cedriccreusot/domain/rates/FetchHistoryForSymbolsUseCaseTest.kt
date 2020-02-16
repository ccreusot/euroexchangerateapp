package fr.cedriccreusot.domain.rates

import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import fr.cedriccreusot.domain.rates.models.DateRate
import fr.cedriccreusot.domain.rates.models.Rate
import fr.cedriccreusot.domain.rates.repositories.GetHistoryRateRepository
import io.kotlintest.matchers.reflection.beLateInit
import io.kotlintest.specs.StringSpec
import org.mockito.Mockito.mock

class FetchHistoryForSymbolsUseCaseTest : StringSpec({

    lateinit var repository : GetHistoryRateRepository
    lateinit var usecase : FetchHistoryForSymbolsUseCase

    fun setup() {
        repository = mock(GetHistoryRateRepository::class.java)
        usecase = FetchHistoryForSymbolsUseCase.createUseCase(repository)
    }

    """
        Given we're fetching the history empty symbols
        When the use case is invoked
        Then the use case should return empty list
    """ {
        setup()

        val result = usecase.invoke("")

        assertThat(result).isEmpty()
        verifyZeroInteractions(repository)
    }

    """
        Given we're fetching the history for symbols
        When repository throw an Exception
        Then the use case should return that exception
    """ {
        setup()
        given(repository.historyRate("USD")).willThrow(Exception())

        val result = kotlin.runCatching {
            usecase.invoke("USD")
        }

        assertThat(result).isNotNull()
        verify(repository).historyRate("USD")
    }

    """
        Given we're fetching the history for symbols
        When repository return list of date rate
        Then the use case should return that list
    """ {
        setup()
        val expectedList = listOf(
            DateRate("2018-06-29", Rate("USD", 1.03.toBigDecimal()))
        )
        given(repository.historyRate("USD")).willReturn(
            expectedList
        )

        val result = usecase.invoke("USD")

        assertThat(result).isNotEmpty()
        assertThat(result).isEqualTo(expectedList)
        verify(repository).historyRate("USD")
    }
})