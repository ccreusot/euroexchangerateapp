package fr.cedriccreusot.euroexchangerate.rates.repositories

import fr.cedriccreusot.euroexchangerate.rates.repositories.services.ExchangeRatesApiService
import fr.cedriccreusot.euroexchangerate.rates.usecases.models.Rate
import fr.cedriccreusot.euroexchangerate.rates.usecases.repositories.GetRatesRepository

class GetRatesRepositoryAdapter(private val service: ExchangeRatesApiService) : GetRatesRepository {
    override fun rates(): List<Rate> {
        return service.latestRates().rates.toList().map {
            Rate(it.first, it.second.toBigDecimal())
        }
    }
}