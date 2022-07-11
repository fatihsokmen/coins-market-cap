package com.github.fatihsokmen.coinsmarketcap.presentation.dashboard

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.paging.LoadState
import com.github.fatihsokmen.coinsmarketcap.R
import com.github.fatihsokmen.coinsmarketcap.databinding.FragmentDashboardBinding
import com.github.fatihsokmen.coinsmarketcap.presentation.*
import com.github.fatihsokmen.coinsmarketcap.presentation.routing.handleRoute
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private val viewModel: DashboardViewModel by viewModel()

    private val binding by viewBinding(FragmentDashboardBinding::bind)

    private var adapter: CryptoAssetsAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        observeViewModel(viewModel, ::handleEvent, ::handleRoute)
        bindOnStart {
            viewModel.items.collect { data ->
                adapter?.submitData(data)
            }
        }
        bindOnStart {
            adapter?.loadStateFlow?.collect { loadStates ->
                binding.root.isRefreshing = (loadStates.refresh is LoadState.Loading)
                binding.retry.isVisible = (loadStates.refresh is LoadState.Error)
                binding.assets.isVisible = (loadStates.refresh !is LoadState.Error)
            }
        }
    }

    private fun handleEvent(event: DashboardEvent) = when (event) {
        DashboardEvent.Refresh -> adapter?.refresh()
    }

    private fun setupUi() {
        adapter = CryptoAssetsAdapter { asset ->
            viewModel.showDetails(asset)
        }
        binding.assets.adapter = adapter
        binding.assets.setHasFixedSize(true)
        binding.root.setOnRefreshListener {
            viewModel.refreshData()
        }
        binding.retry.setOnClickListener {
            viewModel.refreshData()
        }
    }
}