package fr.cedriccreusot.network_adapters.rates.services

import fr.cedriccreusot.network_adapters.rates.models.DateRates
import fr.cedriccreusot.network_adapters.rates.models.Rates
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRatesApiService {
    @Throws(Exception::class)
    fun latestRates() : Rates

    @Throws(Exception::class)
    fun historyRate(startAt: String, endAt: String, rate: String) : DateRates

    companion object {
        fun createService(): ExchangeRatesApiService = ExchangeRatesApiServiceImpl()
    }
}

internal interface ExchangeRatesApiRetrofitService {
    @GET("latest")
    fun latestRates() : Call<Rates>

    @GET("history")
    fun historyRate(@Query("start_at") startAt: String,
                    @Query("end_at") endAt: String,
                    @Query("symbols") symbols: String) : Call<DateRates>
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
            throw Exception(e)
        }
    }

    override fun historyRate(startAt: String, endAt: String, rate: String) : DateRates {
        return try {
            service.historyRate(startAt, endAt, rate).execute().body()!!
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}

