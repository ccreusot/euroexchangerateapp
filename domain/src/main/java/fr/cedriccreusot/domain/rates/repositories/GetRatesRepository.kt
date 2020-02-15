package fr.cedriccreusot.domain.rates.repositories

import fr.cedriccreusot.domain.rates.models.Rate

interface GetRatesRepository {
    @Throws(Exception::class)
    fun rates(): List<Rate>
}