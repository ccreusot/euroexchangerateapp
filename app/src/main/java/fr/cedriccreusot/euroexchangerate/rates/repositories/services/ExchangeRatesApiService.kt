package fr.cedriccreusot.euroexchangerate.rates.repositories.services

import fr.cedriccreusot.euroexchangerate.rates.repositories.models.Rates
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ExchangeRatesApiService {
    @Throws(Exception::class)
    fun latestRates() : Rates

    companion object {
        fun createService(): ExchangeRatesApiService = ExchangeRatesApiServiceImpl()
    }
}

internal interface ExchangeRatesApiRetrofitService {
    @GET("latest")
    fun latestRates() : Call<Rates>
}

internal class ExchangeRatesApiServiceImpl : ExchangeRatesApiService {

    private val service : ExchangeRatesApiRetrofitService = Retrofit.Builder()
        .baseUrl("https://api.exchangeratesapi.io")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ExchangeRatesApiRetrofitService::class.java)

    override fun latestRates(): Rates {
        return try {
            service.latestRates().execute().body()!!
        } catch (e: Exception) {
            throw Exception()
        }
    }
}

