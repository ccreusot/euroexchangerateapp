package fr.cedriccreusot.presentation.rates.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.android.viewmodel.ext.android.viewModel

import fr.cedriccreusot.presentation.databinding.RateDetailFragmentBinding
import fr.cedriccreusot.presentation.rates.detail.viewmodels.RateDetailViewModel

class RateDetailFragment : Fragment() {
    private val rateDetailViewModel : RateDetailViewModel by viewModel()

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
