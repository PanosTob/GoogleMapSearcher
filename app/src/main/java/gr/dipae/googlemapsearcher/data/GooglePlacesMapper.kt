package gr.dipae.googlemapsearcher.data

import gr.dipae.googlemapsearcher.model.GooglePlaces
import gr.dipae.googlemapsearcher.model.GooglePlacesResponse
import gr.dipae.googlemapsearcher.model.MapClusterItem
import gr.dipae.googlemapsearcher.util.Mapper
import javax.inject.Inject

class GooglePlacesMapper @Inject constructor() : Mapper {

    operator fun invoke(response: GooglePlacesResponse): GooglePlaces {
        val googlePlacesList = mutableListOf<MapClusterItem>()
        response.candidates?.forEach {
            googlePlacesList.add(
                MapClusterItem(
                    id = it.name ?: "",
                    latitude = it.geometry?.location?.lat ?: (0).toDouble(),
                    longitude = it.geometry?.location?.lng ?: (0).toDouble()
                )
            )
        }
        return GooglePlaces(googlePlacesList)
    }
}