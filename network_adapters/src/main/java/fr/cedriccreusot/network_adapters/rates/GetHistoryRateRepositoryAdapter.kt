package fr.cedriccreusot.network_adapters.rates

import fr.cedriccreusot.domain.rates.models.DateRate
import fr.cedriccreusot.domain.rates.models.Rate
import fr.cedriccreusot.domain.rates.repositories.GetHistoryRateRepository
import fr.cedriccreusot.network_adapters.rates.services.ExchangeRatesApiService
import java.text.SimpleDateFormat
import java.util.*

class GetHistoryRateRepositoryAdapter(private val service: ExchangeRatesApiService) : GetHistoryRateRepository {
    override fun historyRate(code: String): List<DateRate> {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val year = Calendar.getInstance().get(Calendar.YEAR)
        val currentDate = Calendar.getInstance().time
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year - 1)
        val previousYear = calendar.time

        return service.historyRate(formatter.format(previousYear), formatter.format(currentDate), code).rates.map { dateRate ->
            DateRate(dateRate.key, dateRate.value.map { rate -> Rate(rate.key, rate.value.toBigDecimal()) }[0])
        }
    }
}