package fr.cedriccreusot.presentation.rates.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import org.koin.android.viewmodel.ext.android.viewModel

import fr.cedriccreusot.presentation.databinding.RateDetailFragmentBinding
import fr.cedriccreusot.presentation.rates.detail.viewmodels.RateDetailViewModel
import org.koin.core.parameter.parametersOf

class RateDetailFragment : Fragment() {
    private val arguments : RateDetailFragmentArgs? by navArgs()
    private val rateDetailViewModel : RateDetailViewModel by viewModel { parametersOf(arguments?.code ?: "") }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = RateDetailFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = rateDetailViewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}
