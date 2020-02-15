package fr.cedriccreusot.network_adapters.rates.models

import com.google.gson.annotations.SerializedName

data class Rates(
    @SerializedName("base") val base: String,
    @SerializedName("date") val date: String,
    @SerializedName("rates") val rates: Map<String, Double>
)
