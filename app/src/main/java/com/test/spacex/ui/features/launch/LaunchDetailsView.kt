package com.test.spacex.ui.features.launch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.test.spacex.R
import com.test.spacex.databinding.ViewLaunchDetailsBinding
import com.test.spacex.domain.framework.interfaces.IImageLoader
import com.test.spacex.domain.helpers.toFormattedDate
import com.test.spacex.domain.repository.interfaces.ISpaceXLaunchesRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LaunchDetailsView : Fragment() {

    private lateinit var binding : ViewLaunchDetailsBinding

    private val launchViewModel : LaunchViewModel by activityViewModels()

    @Inject
    lateinit var imageLoader: IImageLoader

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ViewLaunchDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
    }

    private fun setObservers() {
        launchViewModel.selectedItem.observe(viewLifecycleOwner) {
            with(binding) {
                imageLoader.loadImage(ivLaunchPatch, it.launch_patch_image)
                tvLaunchMissionName.text = getString(R.string.mission_name, it.mission_name)
                tvLaunchRocketName.text = getString(R.string.rocket_name, it.rocket_name)
                tvLaunchSiteName.text = getString(R.string.launch_site_name, it.launch_site_name)
                tvLaunchDate.text = getString(R.string.launch_date, it.date_of_launch?.toFormattedDate())
            }
        }
    }
}