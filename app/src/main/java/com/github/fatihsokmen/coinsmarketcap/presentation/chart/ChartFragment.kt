package com.github.fatihsokmen.coinsmarketcap.presentation.chart

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.github.fatihsokmen.coinsmarketcap.R
import com.github.fatihsokmen.coinsmarketcap.databinding.FragmentChartBinding
import com.github.fatihsokmen.coinsmarketcap.presentation.bindOnStart
import com.github.fatihsokmen.coinsmarketcap.presentation.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.robinhood.spark.animation.MorphSparkAnimator
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ChartFragment : BottomSheetDialogFragment(R.layout.fragment_chart) {

    private val viewModel: ChartViewModel by viewModel {
        parametersOf(arguments?.getParcelable("parcel"))
    }

    private val binding by viewBinding(FragmentChartBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        bindOnStart {
            viewModel.chartData.collect { data ->
                binding.chart.adapter = ChartAdapter(data.map { it.price })
            }
        }
        bindOnStart {
            viewModel.showProgress.collect { progressing ->
                binding.progress.isVisible = progressing
            }
        }
    }

    private fun setupUi() {
        binding.toolbar.title = viewModel.parcel.name
        binding.chart.sparkAnimator = MorphSparkAnimator()
    }
}