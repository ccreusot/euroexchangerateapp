package fr.cedriccreusot.presentation.rates.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import fr.cedriccreusot.presentation.databinding.RateListFragmentBinding
import fr.cedriccreusot.presentation.rates.list.viewmodels.RateListViewModel
import fr.cedriccreusot.presentation.rates.routes.RatesRouter
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class RateListFragment : Fragment() {
    private val rateListViewModel: RateListViewModel by viewModel { parametersOf(this.findNavController())}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = RateListFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = rateListViewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}
