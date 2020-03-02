package fr.cedriccreusot.presentation.rates.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import fr.cedriccreusot.presentation.R
import fr.cedriccreusot.presentation.rates.list.adapters.RateListAdapter
import fr.cedriccreusot.presentation.rates.list.bindingadapters.isLoading
import fr.cedriccreusot.presentation.rates.list.viewmodels.RateListViewModel
import fr.cedriccreusot.presentation.rates.routes.RatesRouter
import kotlinx.android.synthetic.main.rate_list_fragment.view.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.util.*


class RateListFragment : Fragment() {
    private val rateListViewModel: RateListViewModel by viewModel { parametersOf(this.findNavController())}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.rate_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rateListViewModel.isLoading.observe(this) { isLoading ->
            with(view) {
                if (isLoading) {
                    contentViewFlipper.displayedChild = 0
                } else {
                    contentViewFlipper.displayedChild = 1
                }
            }
        }

        rateListViewModel.rateList.observe(this) { rates ->
            with(view) {
                ratesListRecyclerView.adapter = RateListAdapter().apply {
                    submitList(rates)
                }
            }
        }
    }
}
