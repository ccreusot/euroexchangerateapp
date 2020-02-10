package fr.cedriccreusot.euroexchangerate.rates.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.cedriccreusot.euroexchangerate.databinding.RateListFragmentBinding
import fr.cedriccreusot.euroexchangerate.rates.screens.viewmodels.RateListViewModel
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
}
