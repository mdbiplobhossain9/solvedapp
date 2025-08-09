package com.bitlypro.myapp.ui.charts

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bitlypro.myapp.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class ChartFragment : Fragment(R.layout.fragment_chart) {

    private lateinit var lineChart: LineChart
    private lateinit var viewModel: ChartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChartViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lineChart = view.findViewById(R.id.lineChart)

        viewModel.getSpeedTestResults().observe(viewLifecycleOwner) { results ->
            updateChart(results)
        }
    }

    private fun updateChart(results: List<SpeedTestResult>) {
        val entries = results.mapIndexed { index, result -> 
            Entry(index.toFloat(), result.speed.toFloat()) 
        }
        val dataSet = LineDataSet(entries, "Speed Test Results")
        val lineData = LineData(dataSet)
        lineChart.data = lineData
        lineChart.invalidate() // refresh
    }
}