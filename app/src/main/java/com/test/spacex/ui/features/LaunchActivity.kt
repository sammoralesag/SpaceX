package com.test.spacex.ui.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.test.spacex.R
import com.test.spacex.databinding.ActivityLaunchBinding
import com.test.spacex.domain.repository.interfaces.ISpaceXLaunchesRepository
import com.test.spacex.ui.features.launch.LaunchViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LaunchActivity : AppCompatActivity() {

    private val launchViewModel: LaunchViewModel by viewModels()

    @Inject
    lateinit var repository: ISpaceXLaunchesRepository

    lateinit var binding: ActivityLaunchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaunchBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}