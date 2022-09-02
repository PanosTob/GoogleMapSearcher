package gr.dipae.googlemapsearcher.data

import gr.dipae.googlemapsearcher.model.GooglePlaces
import javax.inject.Inject

interface MapSearcherRepository {
    suspend fun getGooglePlaces(query: String): GooglePlaces
}

class MapSearcherRepositoryImpl @Inject constructor(
    private val dataSource: MapSearcherDataSource,
    private val googlePlacesMapper: GooglePlacesMapper
): MapSearcherRepository {
    override suspend fun getGooglePlaces(query: String): GooglePlaces {
        return googlePlacesMapper(dataSource.getGooglePlaces(query))
    }
}