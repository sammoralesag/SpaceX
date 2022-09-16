package com.test.spacex.ui.features.launch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.spacex.domain.models.Launch
import com.test.spacex.domain.models.RepositoryResponse
import com.test.spacex.domain.repository.interfaces.ISpaceXLaunchesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LaunchViewModel @Inject constructor(private val spaceXLaunchesRepository: ISpaceXLaunchesRepository): ViewModel() {

    private val _launchUIEvents = Channel<LaunchUIEvents>()
    val launchUIEvents = _launchUIEvents.receiveAsFlow()

    private var _launchList = MutableLiveData<List<Launch>?>()
    val launchList : LiveData<List<Launch>?>
        get() = _launchList

    private var _selectedItem = MutableLiveData<Launch>()
    val selectedItem : LiveData<Launch>
        get() = _selectedItem

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getLaunches()
        }
    }

    private suspend fun getLaunches() {
        when(val response = spaceXLaunchesRepository.getLaunches()){
            is RepositoryResponse.Failed -> _launchUIEvents.trySend(LaunchUIEvents.ShowErrorEvent(response.reason))
            is RepositoryResponse.Success -> {
                withContext(Dispatchers.Main) {
                    _launchList.value = response.data
                }
            }
        }
    }

    fun setSelectedLaunch(launch: Launch) {
        _selectedItem.value = launch
    }

}

sealed class LaunchUIEvents{
    data class ShowErrorEvent(val message:String) : LaunchUIEvents()
}
