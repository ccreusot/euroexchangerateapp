package fr.cedriccreusot.domain.rates.models

import java.math.BigDecimal

data class Rate(val code: String, val ratio: BigDecimal)