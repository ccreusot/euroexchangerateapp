package fr.cedriccreusot.euroexchangerate.rates.usecases

import fr.cedriccreusot.euroexchangerate.rates.usecases.models.Rate
import fr.cedriccreusot.euroexchangerate.rates.usecases.repositories.GetRatesRepository

interface FetchLatestRatesUseCase {
    fun invoke() : List<Rate>

    companion object {
        fun createUseCase(repository: GetRatesRepository) : FetchLatestRatesUseCase = FetchLatestRatesUseCaseImpl(repository)
    }
}

internal class FetchLatestRatesUseCaseImpl(private val repository: GetRatesRepository) : FetchLatestRatesUseCase {
    override fun invoke(): List<Rate> {
        return repository.rates()
    }
}