package fr.cedriccreusot.presentation.rates

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.cedriccreusot.presentation.databinding.RateListFragmentBinding
import fr.cedriccreusot.presentation.rates.viewmodels.RateListViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class RateListFragment : Fragment() {
    private val rateListViewModel: RateListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = RateListFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = rateListViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rateListViewModel.fetchRates()
    }
}
