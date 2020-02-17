package fr.cedriccreusot.presentation.rates.detail.viewmodels

import com.google.common.truth.Truth.assertThat
import fr.cedriccreusot.domain.rates.models.DateRate
import fr.cedriccreusot.domain.rates.models.Rate
import io.kotlintest.specs.StringSpec

class DateRateViewModelTest : StringSpec({
    """
        Given initialize viewModel
        When printing the view model
        Then should shoud print the "code : ratio EUR"
    """ {
        val printed = DateRateViewModel(DateRate("2018-01-01", Rate("USD", 1.294.toBigDecimal()))).print()

        assertThat(printed).isEqualTo("2018-01-01 1.294 EUR")
    }

})