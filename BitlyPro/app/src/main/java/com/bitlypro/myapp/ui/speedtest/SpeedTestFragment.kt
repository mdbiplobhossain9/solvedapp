package com.bitlypro.myapp.ui.speedtest

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bitlypro.myapp.R
import com.bitlypro.myapp.databinding.FragmentSpeedTestBinding
import com.bitlypro.myapp.utils.NetworkUtils
import kotlinx.coroutines.launch

class SpeedTestFragment : Fragment() {
    
    private var _binding: FragmentSpeedTestBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: SpeedTestViewModel by viewModels()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSpeedTestBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupObservers()
        setupClickListeners()
        updateConnectionInfo()
    }
    
    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.speedTestState.collect { state ->
                updateUI(state)
            }
        }
    }
    
    private fun setupClickListeners() {
        binding.btnStartTest.setOnClickListener {
            if (NetworkUtils.isNetworkAvailable(requireContext())) {
                viewModel.startSpeedTest()
            } else {
                // Show no internet message
                binding.tvStatus.text = getString(R.string.no_internet)
            }
        }
        
        binding.btnStopTest.setOnClickListener {
            viewModel.stopSpeedTest()
        }
        
        binding.btnShare.setOnClickListener {
            shareResult()
        }
    }
    
    private fun updateUI(state: SpeedTestViewModel.SpeedTestState) {
        binding.apply {
            // Update progress bar
            progressBar.visibility = if (state.isRunning) View.VISIBLE else View.GONE
            progressBar.progress = state.progress
            
            // Update buttons
            btnStartTest.isEnabled = !state.isRunning
            btnStopTest.isEnabled = state.isRunning
            btnShare.isEnabled = !state.isRunning && state.downloadSpeed > 0
            
            // Update status text
            tvStatus.text = state.currentTest
            
            // Update speed displays
            tvDownloadSpeed.text = String.format("%.1f", state.downloadSpeed)
            tvUploadSpeed.text = String.format("%.1f", state.uploadSpeed)
            tvPing.text = if (state.ping > 0) "${state.ping}" else "--"
            
            // Update quality indicators
            tvDownloadQuality.text = NetworkUtils.getSpeedQualityDescription(state.downloadSpeed)
            tvUploadQuality.text = NetworkUtils.getSpeedQualityDescription(state.uploadSpeed)
            
            // Show error if any
            if (state.error != null) {
                tvStatus.text = state.error
            }
        }
    }
    
    private fun updateConnectionInfo() {
        val connectionType = NetworkUtils.getConnectionType(requireContext())
        binding.tvConnectionType.text = getString(R.string.connection_type, connectionType)
    }
    
    private fun shareResult() {
        val state = viewModel.speedTestState.value
        val shareText = """
            Bitly Pro Speed Test Results:
            
            Download: ${String.format("%.1f", state.downloadSpeed)} Mbps
            Upload: ${String.format("%.1f", state.uploadSpeed)} Mbps
            Ping: ${state.ping} ms
            
            Connection: ${NetworkUtils.getConnectionType(requireContext())}
            
            Test your internet speed with Bitly Pro!
        """.trimIndent()
        
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, shareText)
        }
        startActivity(Intent.createChooser(intent, "Share Speed Test Result"))
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}