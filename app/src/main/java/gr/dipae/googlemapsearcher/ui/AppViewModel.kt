package gr.dipae.googlemapsearcher.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import gr.dipae.googlemapsearcher.util.ConnectivityLiveData
import gr.dipae.googlemapsearcher.util.SingleLiveEvent
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
) : BaseViewModel() {

    private val _locationServicesUI = SingleLiveEvent<Unit>()
    val locationServicesUI: LiveData<Unit> = _locationServicesUI

    fun showLocationOnMap() {
        _locationServicesUI.value = Unit
    }
}