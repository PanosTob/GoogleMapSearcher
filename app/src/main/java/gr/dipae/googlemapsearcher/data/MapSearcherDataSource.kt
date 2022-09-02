package gr.dipae.googlemapsearcher.data

import android.annotation.SuppressLint
import android.location.LocationManager
import androidx.core.location.LocationManagerCompat
import com.google.android.gms.location.FusedLocationProviderClient
import gr.dipae.googlemapsearcher.model.GooglePlacesResponse
import gr.dipae.googlemapsearcher.model.UserLocation
import gr.dipae.googlemapsearcher.util.requireNotNull
import gr.dipae.googlemapsearcher.util.suspended
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface MapSearcherDataSource {
    suspend fun getGooglePlaces(query: String): GooglePlacesResponse
    suspend fun getUserLocation(): UserLocation
    fun isLocationServiceEnabled(): Boolean
}

class MapSearcherDataSourceImpl @Inject constructor(
    private val api: GooglePlacesApi,
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    private val locationManager: LocationManager,
): MapSearcherDataSource {
    override suspend fun getGooglePlaces(query: String): GooglePlacesResponse {
        return withContext(Dispatchers.IO) {
            api.getGooglePlaces(query).requireNotNull()
        }
    }

    override fun isLocationServiceEnabled(): Boolean = LocationManagerCompat.isLocationEnabled(locationManager)

    @SuppressLint("MissingPermission")
    override suspend fun getUserLocation(): UserLocation {
        val location = fusedLocationProviderClient.lastLocation.suspended()
        return UserLocation(
            location.latitude,
            location.longitude
        )
    }
}

