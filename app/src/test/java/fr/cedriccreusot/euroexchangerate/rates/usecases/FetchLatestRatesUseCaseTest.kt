package fr.cedriccreusot.euroexchangerate.rates.usecases

import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import fr.cedriccreusot.euroexchangerate.rates.usecases.repositories.GetRatesRepository
import io.kotlintest.specs.StringSpec
import org.mockito.Mockito.mock

class FetchLatestRatesUseCaseTest : StringSpec ({

    """Given we're fetching the latest rate
        |When the repository throw an Exception
        |Then the use case should return that Exception""" {
        val repository = mock(GetRatesRepository::class.java)
        val usecase = FetchLatestRatesUseCaseImpl(repository)

        given(repository.rates()).willThrow(Exception())

        val result = runCatching {
            usecase.invoke()
        }

        assertThat(result.exceptionOrNull()).isNotNull()
        verify(repository).rates()
    }
})