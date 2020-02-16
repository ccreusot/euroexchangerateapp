package fr.cedriccreusot.domain.rates

import fr.cedriccreusot.domain.rates.models.DateRate
import fr.cedriccreusot.domain.rates.repositories.GetHistoryRateRepository

interface FetchHistoryForSymbolsUseCase {
    @Throws(Exception::class)
    fun invoke(code: String): List<DateRate>

    companion object {
        fun createUseCase(repository: GetHistoryRateRepository) : FetchHistoryForSymbolsUseCase {
            return FetchHistoryForSymbolsUseCaseImpl(repository)
        }
    }
}

internal class FetchHistoryForSymbolsUseCaseImpl(private val repository: GetHistoryRateRepository) : FetchHistoryForSymbolsUseCase {
    override fun invoke(code: String): List<DateRate> {
        if (code.isEmpty()) {
            return emptyList()
        }
        return repository.historyRate(code)
    }
}