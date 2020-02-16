package fr.cedriccreusot.network_adapters.rates

import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import fr.cedriccreusot.network_adapters.rates.models.Rates
import fr.cedriccreusot.network_adapters.rates.services.ExchangeRatesApiService
import fr.cedriccreusot.domain.rates.models.Rate
import io.kotlintest.specs.StringSpec
import org.mockito.Mockito.mock

class GetRatesRepositoryAdapterTest : StringSpec({

    """
        Given the get rates repository
        When this repository get rates
        And retrofit services throw an Exception
        Then this repository should throw an Exception too.
    """ {
        val service = mock(ExchangeRatesApiService::class.java)
        val repository =
            GetRatesRepositoryAdapter(
                service
            )

        given(service.latestRates()).willThrow(Exception())

        val result = runCatching {
            repository.rates()
        }

        assertThat(result).isNotNull()
        verify(service).latestRates()
    }

    """
        Given the get rates repository
        When this repository get rates
        And retrofit services return an object Rates
        Then this repository should return a list of Rate.
    """ {
        val service = mock(ExchangeRatesApiService::class.java)
        val repository =
            GetRatesRepositoryAdapter(
                service
            )
        val rates = Rates(
            base = "EUR",
            date = "date",
            rates = mapOf(
                "USD" to 1.34,
                "ZEN" to 0.23
            )
        )
        val expected = listOf(
            Rate(
                "USD",
                1.34.toBigDecimal()
            ),
            Rate("ZEN", 0.23.toBigDecimal())
        )

        given(service.latestRates()).willReturn(rates)

        val result = repository.rates()

        assertThat(result).isNotNull()
        assertThat(result).isEqualTo(expected)
        verify(service).latestRates()
    }
})