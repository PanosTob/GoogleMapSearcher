package gr.dipae.googlemapsearcher.data

import gr.dipae.googlemapsearcher.model.GooglePlacesResponse
import gr.dipae.googlemapsearcher.util.requireNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface MapSearcherDataSource {
    suspend fun getGooglePlaces(query: String): GooglePlacesResponse
}

class MapSearcherDataSourceImpl @Inject constructor(
    private val api: GooglePlacesApi
): MapSearcherDataSource {
    override suspend fun getGooglePlaces(query: String): GooglePlacesResponse {
        return withContext(Dispatchers.IO) {
            api.getGooglePlaces(query).requireNotNull()
        }
    }
}

