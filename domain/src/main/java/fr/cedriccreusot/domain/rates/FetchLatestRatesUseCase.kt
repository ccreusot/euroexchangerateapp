package fr.cedriccreusot.domain.rates

import fr.cedriccreusot.domain.rates.models.Rate
import fr.cedriccreusot.domain.rates.repositories.GetRatesRepository

interface FetchLatestRatesUseCase {
    fun invoke() : List<Rate>

    companion object {
        fun createUseCase(repository: GetRatesRepository) : FetchLatestRatesUseCase =
            FetchLatestRatesUseCaseImpl(repository)
    }
}

internal class FetchLatestRatesUseCaseImpl(private val repository: GetRatesRepository) :
    FetchLatestRatesUseCase {
    override fun invoke(): List<Rate> {
        return repository.rates()
    }
}