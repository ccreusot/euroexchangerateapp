package fr.cedriccreusot.presentation.rates.list.viewmodels

import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.verify
import fr.cedriccreusot.domain.rates.models.Rate
import fr.cedriccreusot.presentation.rates.routes.RatesRouter
import io.kotlintest.specs.StringSpec
import org.mockito.Mockito.mock

class RateViewModelTest : StringSpec({
    """
        Given initialize viewModel
        When printing the view model
        Then should shoud print the "code : ratio EUR"
    """ {
        val router = mock(RatesRouter::class.java)
        val printed = RateViewModel(Rate("USD", 1.294.toBigDecimal()), router).print()

        assertThat(printed).isEqualTo("USD : 1.294 EUR")
    }

    """
        Given initialize viewModel
        When we open the detail
        Then should call rates router on code USD
    """ {
        val router = mock(RatesRouter::class.java)
        RateViewModel(Rate("USD", 1.294.toBigDecimal()), router).openDetail()

        verify(router).getToDetails("USD")
    }
})