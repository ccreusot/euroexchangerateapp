package fr.cedriccreusot.domain.rates.repositories

import fr.cedriccreusot.domain.rates.models.DateRate

interface GetHistoryRateRepository {
    @Throws(Exception::class)
    fun historyRate(code: String): List<DateRate>
}