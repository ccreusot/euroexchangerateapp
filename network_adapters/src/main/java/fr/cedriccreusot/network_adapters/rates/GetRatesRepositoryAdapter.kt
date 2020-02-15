package fr.cedriccreusot.network_adapters.rates

import fr.cedriccreusot.network_adapters.rates.services.ExchangeRatesApiService
import fr.cedriccreusot.domain.rates.models.Rate
import fr.cedriccreusot.domain.rates.repositories.GetRatesRepository

class GetRatesRepositoryAdapter(private val service: ExchangeRatesApiService) : GetRatesRepository {
    override fun rates(): List<Rate> {
        return service.latestRates().rates.toList().map {
            Rate(
                it.first,
                it.second.toBigDecimal()
            )
        }
    }
}