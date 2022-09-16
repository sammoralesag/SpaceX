package com.test.spacex.ui.features.launch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.test.spacex.R
import com.test.spacex.databinding.ViewLaunchHistoryBinding
import com.test.spacex.domain.models.Launch

class LaunchHistoryView : Fragment() {

    private lateinit var binding : ViewLaunchHistoryBinding

    private val launchViewModel : LaunchViewModel by activityViewModels()

    private val launchAdapter by lazy { LaunchAdapter(context, ::onLaunchSelected) }

    private fun onLaunchSelected(launch: Launch, lastPosition : Int?, currentPosition : Int?) {
        launchViewModel.setSelectedLaunch(launch)
        val isTablet = resources.getBoolean(R.bool.isTablet)
        if (isTablet){
            lastPosition?.let { launchAdapter.notifyItemChanged(it) }
            currentPosition?.let { launchAdapter.notifyItemChanged(it) }
            val navHostFragment = childFragmentManager.findFragmentById(R.id.fragment_container_detail) as NavHostFragment?
            navHostFragment?.navController?.navigate(R.id.launchDetailsView)
        } else {
            findNavController().navigate(R.id.action_launchHistoryView_to_launchDetailsView)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ViewLaunchHistoryBinding.inflate(inflater, container, false)
        lifecycleScope.launchWhenStarted {
            setObservers()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvLaunchList.adapter = launchAdapter
    }

    private suspend fun setObservers() {

        launchViewModel.launchList.observe(viewLifecycleOwner) {
            launchAdapter.submitList(it)
        }

        launchViewModel.launchUIEvents.collect {
            when(it){
                is LaunchUIEvents.ShowErrorEvent -> Snackbar.make(binding.root, it.message, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

}