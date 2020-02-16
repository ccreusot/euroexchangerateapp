package fr.cedriccreusot.network_adapters.rates.models

import com.google.gson.annotations.SerializedName

data class DateRates(
    @SerializedName("base") val base: String,
    @SerializedName("start_at") val startAt: String,
    @SerializedName("end_at") val endAt: String,
    @SerializedName("rates") val rates: Map<String, Map<String, Double>>
)
