package gr.dipae.googlemapsearcher.data

import android.annotation.SuppressLint
import com.google.android.gms.location.FusedLocationProviderClient
import gr.dipae.googlemapsearcher.model.GooglePlaces
import gr.dipae.googlemapsearcher.model.UserLocation
import gr.dipae.googlemapsearcher.util.suspended
import javax.inject.Inject

interface MapSearcherRepository {
    suspend fun getGooglePlaces(location: String, query: String): GooglePlaces
    suspend fun getUserLocation(): UserLocation
    fun isLocationServiceEnabled(): Boolean
}

class MapSearcherRepositoryImpl @Inject constructor(
    private val dataSource: MapSearcherDataSource,
    private val googlePlacesMapper: GooglePlacesMapper
): MapSearcherRepository {
    override suspend fun getGooglePlaces(location: String, query: String): GooglePlaces {
        return googlePlacesMapper(dataSource.getGooglePlaces(location, query))
    }

    override fun isLocationServiceEnabled(): Boolean = dataSource.isLocationServiceEnabled()

    override suspend fun getUserLocation(): UserLocation {
        return dataSource.getUserLocation()
    }
}