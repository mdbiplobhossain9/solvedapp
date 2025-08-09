package com.bitlypro.myapp.ui.speedtest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bitlypro.myapp.R
import com.bitlypro.myapp.ui.speedtest.SpeedTestViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class SpeedTestFragment : Fragment() {

    private val viewModel: SpeedTestViewModel by viewModels()

    private lateinit var startButton: MaterialButton
    private lateinit var stopButton: MaterialButton
    private lateinit var resultTextView: MaterialTextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_speed_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startButton = view.findViewById(R.id.start_button)
        stopButton = view.findViewById(R.id.stop_button)
        resultTextView = view.findViewById(R.id.result_text_view)

        startButton.setOnClickListener {
            viewModel.startSpeedTest()
        }

        stopButton.setOnClickListener {
            viewModel.stopSpeedTest()
        }

        viewModel.speedTestResult.observe(viewLifecycleOwner) { result ->
            resultTextView.text = getString(R.string.speed_test_result, result)
        }
    }
}