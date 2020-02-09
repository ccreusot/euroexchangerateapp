package fr.cedriccreusot.euroexchangerate.rates.usecases

import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import fr.cedriccreusot.euroexchangerate.rates.usecases.models.Rate
import fr.cedriccreusot.euroexchangerate.rates.usecases.repositories.GetRatesRepository
import io.kotlintest.TestCase
import io.kotlintest.specs.StringSpec
import org.mockito.Mockito.mock
import kotlin.math.exp

class FetchLatestRatesUseCaseTest : StringSpec ({

    lateinit var repository : GetRatesRepository
    lateinit var usecase: FetchLatestRatesUseCase

    fun setup() {
        repository = mock(GetRatesRepository::class.java)
        usecase = FetchLatestRatesUseCase.createUseCase(repository)
    }

    """Given we're fetching the latest rate
        When the repository throw an Exception
        Then the use case should return that Exception""" {
        setup()
        given(repository.rates()).willThrow(Exception())

        val result = runCatching {
            usecase.invoke()
        }

        assertThat(result.exceptionOrNull()).isNotNull()
        verify(repository).rates()
    }

    """Given we're fetching the latest rate
        When the repository return a list of rates
        Then the use case should return it""" {
        setup()
        val expected = listOf(
            Rate("USD", 1.305.toBigDecimal()),
            Rate("PNL", 1.305.toBigDecimal())
        )

        given(repository.rates()).willReturn(expected)

        val result = usecase.invoke()

        assertThat(result).isNotNull()
        assertThat(result).isEqualTo(expected)
        verify(repository).rates()
    }
})