package fr.cedriccreusot.euroexchangerate

import android.app.Application
import androidx.navigation.NavController
import fr.cedriccreusot.domain.rates.FetchHistoryForSymbolsUseCase
import fr.cedriccreusot.network_adapters.rates.GetRatesRepositoryAdapter
import fr.cedriccreusot.network_adapters.rates.services.ExchangeRatesApiService
import fr.cedriccreusot.presentation.rates.list.viewmodels.RateListViewModel
import fr.cedriccreusot.domain.rates.FetchLatestRatesUseCase
import fr.cedriccreusot.domain.rates.repositories.GetHistoryRateRepository
import fr.cedriccreusot.domain.rates.repositories.GetRatesRepository
import fr.cedriccreusot.network_adapters.rates.GetHistoryRateRepositoryAdapter
import fr.cedriccreusot.presentation.rates.detail.viewmodels.RateDetailViewModel
import fr.cedriccreusot.presentation.rates.routes.RatesRouter
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

class EuroExchangeRateApplication : Application() {

    private val appModule = module {
        single<GetRatesRepository> {
            GetRatesRepositoryAdapter(
                get()
            )
        }
        single<GetHistoryRateRepository> {
            GetHistoryRateRepositoryAdapter(
                get()
            )
        }
        single { ExchangeRatesApiService.createService() }
        single { FetchLatestRatesUseCase.createUseCase(get()) }
        single { FetchHistoryForSymbolsUseCase.createUseCase(get()) }
        single { (navController: NavController) -> RatesRouter.createRoutes(navController)}
        viewModel { (navController: NavController) -> RateListViewModel(get(), get { parametersOf(navController) }) }
        viewModel { RateDetailViewModel(get()) }
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