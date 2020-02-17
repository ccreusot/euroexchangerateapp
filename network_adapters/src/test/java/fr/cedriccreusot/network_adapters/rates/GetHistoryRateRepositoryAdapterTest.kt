package fr.cedriccreusot.network_adapters.rates

import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import fr.cedriccreusot.domain.rates.models.DateRate
import fr.cedriccreusot.domain.rates.models.Rate
import fr.cedriccreusot.network_adapters.rates.models.DateRates
import fr.cedriccreusot.network_adapters.rates.services.ExchangeRatesApiService
import io.kotlintest.specs.StringSpec
import org.mockito.Mockito.mock
import java.text.SimpleDateFormat
import java.util.*

class GetHistoryRateRepositoryAdapterTest : StringSpec({
    """
        Given the get rates repository
        When this repository get rates
        And retrofit services throw an Exception
        Then this repository should throw an Exception too.
    """ {
        val service = mock(ExchangeRatesApiService::class.java)
        val repository =
            GetHistoryRateRepositoryAdapter(
                service
            )
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        val currentDate = Calendar.getInstance().time
        val currentDateFormatted = simpleDateFormat.format(currentDate)
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR) - 1)
        val startDateFormatted = simpleDateFormat.format(
            calendar.time)

        given(service.historyRate(startDateFormatted, currentDateFormatted, "USD")).willThrow(Exception())

        val result = runCatching {
            repository.historyRate("USD")
        }

        assertThat(result).isNotNull()
        verify(service).historyRate(startDateFormatted, currentDateFormatted, "USD")
    }

    """
        Given the get rates repository
        When this repository get rates
        And retrofit services return an object DateRates
        Then this repository should return a list of DateRate.
    """ {
        val service = mock(ExchangeRatesApiService::class.java)
        val repository =
            GetHistoryRateRepositoryAdapter(
                service
            )
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        val currentDate = Calendar.getInstance().time
        val currentDateFormatted = simpleDateFormat.format(currentDate)
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR) - 1)
        val startDateFormatted = simpleDateFormat.format(
            calendar.time)

        given(service.historyRate(startDateFormatted, currentDateFormatted, "USD")).willReturn(
            DateRates(
                base = "EUR",
                startAt = startDateFormatted,
                endAt = currentDateFormatted,
                rates = mapOf(
                    currentDateFormatted to mapOf("USD" to 1.2)
                )
            )
        )

        val result = repository.historyRate("USD")

        assertThat(result).isNotNull()
        assertThat(result).isNotEmpty()
        assertThat(result).isEqualTo(listOf(
            DateRate(currentDateFormatted, Rate("USD", 1.2.toBigDecimal()))
        ))
        verify(service).historyRate(startDateFormatted, currentDateFormatted, "USD")
    }
})