package com.github.fatihsokmen.coinsmarketcap.presentation.chart

import com.robinhood.spark.SparkAdapter

class ChartAdapter(private val data: List<Float>) : SparkAdapter() {

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(index: Int): Any {
        return data[index]
    }

    override fun getY(index: Int): Float {
        return data[index]
    }
}