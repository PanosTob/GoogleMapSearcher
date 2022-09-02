package gr.dipae.googlemapsearcher.ui

import androidx.lifecycle.LiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import gr.dipae.googlemapsearcher.R
import gr.dipae.googlemapsearcher.model.MapClusterItem
import gr.dipae.googlemapsearcher.util.SingleLiveEvent
import gr.dipae.googlemapsearcher.model.UserLocation
import gr.dipae.googlemapsearcher.model.UserLocationResultType
import gr.dipae.googlemapsearcher.usecases.GetGooglePlacesUseCase
import gr.dipae.googlemapsearcher.usecases.GetUserLocationUseCase
import gr.dipae.googlemapsearcher.usecases.IsLocationServiceEnabledUseCase
import javax.inject.Inject

@HiltViewModel
class MapSearcherViewModel @Inject constructor(
    private val getGooglePlacesUseCase: GetGooglePlacesUseCase,
    private val getUserLocationUseCase: GetUserLocationUseCase,
    private val isLocationServiceEnabledUseCase: IsLocationServiceEnabledUseCase
): BaseViewModel() {

    private val _showUserLocationOnMapUI = SingleLiveEvent<UserLocation>()
    val showUserLocationOnMapUI: LiveData<UserLocation> = _showUserLocationOnMapUI

    private val _initUserLocationUI = SingleLiveEvent<Unit>()
    val initUserLocationUI: LiveData<Unit> = _initUserLocationUI

    private val _showGooglePlacesUI = SingleLiveEvent<List<MapClusterItem>>()
    val showGooglePlacesUI: LiveData<List<MapClusterItem>> = _showGooglePlacesUI

    private var userLocation: UserLocation? = null
    fun centerOnUserLocation() {
        launch {
            when (val result = getUserLocationUseCase()) {
                is UserLocationResultType.Success -> result.userLocation.let {
                    _showUserLocationOnMapUI.value = it
                    userLocation = it
                }
                is UserLocationResultType.GpsNotEnabled -> errorLiveData.value = R.string.error_no_gps
                is UserLocationResultType.Failure -> errorLiveData.value = R.string.error_generic
            }
        }
    }

    fun initUserLocation() {
        if (isLocationServiceEnabledUseCase()) {
            _initUserLocationUI.value = Unit
        }
    }

    fun searchForGooglePlaces(query: String) {
        launchWithProgress {
            userLocation?.let {
                _showGooglePlacesUI.value = getGooglePlacesUseCase("${it.latitude},${it.longitude}", query).clusterItems
            }
        }
    }
}