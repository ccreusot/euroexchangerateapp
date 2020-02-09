package fr.cedriccreusot.euroexchangerate.rates.usecases.models

import java.math.BigDecimal

data class Rate(val code: String, val ratio: BigDecimal)