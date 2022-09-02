package gr.dipae.googlemapsearcher.ui

import androidx.lifecycle.LiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import gr.dipae.googlemapsearcher.model.MapClusterItem
import gr.dipae.googlemapsearcher.util.SingleLiveEvent
import gr.dipae.googlemapsearcher.model.UserLocation
import gr.dipae.googlemapsearcher.usecases.GetGooglePlacesUseCase
import javax.inject.Inject

@HiltViewModel
class MapSearcherViewModel @Inject constructor(
    private val getGooglePlacesUseCase: GetGooglePlacesUseCase
): BaseViewModel() {

    private val _showUserLocationOnMapUI = SingleLiveEvent<UserLocation>()
    val showUserLocationOnMapUI: LiveData<UserLocation> = _showUserLocationOnMapUI

    private val _showGooglePlacesUI = SingleLiveEvent<List<MapClusterItem>>()
    val showGooglePlacesUI: LiveData<List<MapClusterItem>> = _showGooglePlacesUI

    /*fun centerOnUserLocation() {
        launch {
            when (val result = getUserLocationUseCase()) {
                is UserLocationResultType.Success -> result.userLocation.let { _showUserLocationOnMapUI.value = it }
                is UserLocationResultType.GpsNotEnabled -> errorLiveData.value = R.string.error_no_gps
                is UserLocationResultType.Failure -> errorLiveData.value = R.string.error_generic
            }
        }
    }*/

    fun searchForGooglePlaces(query: String) {
        launchWithProgress {
            _showGooglePlacesUI.value = getGooglePlacesUseCase(query).clusterItems
        }
    }
}