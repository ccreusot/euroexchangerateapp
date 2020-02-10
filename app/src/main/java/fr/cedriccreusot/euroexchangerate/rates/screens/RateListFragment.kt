package fr.cedriccreusot.euroexchangerate.rates.screens

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.cedriccreusot.euroexchangerate.R
import fr.cedriccreusot.euroexchangerate.rates.screens.viewmodels.RateListViewModel


class RateListFragment : Fragment() {
    private lateinit var viewModel: RateListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.rate_list_fragment, container, false)
    }
}
