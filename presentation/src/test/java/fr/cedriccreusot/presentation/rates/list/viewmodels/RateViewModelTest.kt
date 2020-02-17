package fr.cedriccreusot.presentation.rates.list.viewmodels

import com.google.common.truth.Truth.assertThat
import fr.cedriccreusot.domain.rates.models.Rate
import io.kotlintest.specs.StringSpec

class RateViewModelTest : StringSpec({
    """
        Given initialize viewModel
        When printing the view model
        Then should shoud print the "code : ratio EUR"
    """ {
        val printed = RateViewModel(Rate("USD", 1.294.toBigDecimal())).print()

        assertThat(printed).isEqualTo("USD : 1.294 EUR")
    }
})