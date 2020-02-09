package fr.cedriccreusot.euroexchangerate.rates.usecases.repositories

import fr.cedriccreusot.euroexchangerate.rates.usecases.models.Rate

interface GetRatesRepository {
    @Throws(Exception::class)
    fun rates(): List<Rate>
}