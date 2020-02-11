package fr.cedriccreusot.euroexchangerate

import android.app.Application
import fr.cedriccreusot.euroexchangerate.rates.repositories.GetRatesRepositoryAdapter
import fr.cedriccreusot.euroexchangerate.rates.repositories.services.ExchangeRatesApiService
import fr.cedriccreusot.euroexchangerate.rates.screens.viewmodels.RateListViewModel
import fr.cedriccreusot.euroexchangerate.rates.usecases.FetchLatestRatesUseCase
import fr.cedriccreusot.euroexchangerate.rates.usecases.repositories.GetRatesRepository
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class EuroExchangeRateApplication : Application() {

    private val appModule = module {
        single<GetRatesRepository> { GetRatesRepositoryAdapter(ExchangeRatesApiService.createService()) }
        single { FetchLatestRatesUseCase.createUseCase(get()) }
        viewModel { RateListViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@EuroExchangeRateApplication)
            modules(appModule)
        }
    }
}