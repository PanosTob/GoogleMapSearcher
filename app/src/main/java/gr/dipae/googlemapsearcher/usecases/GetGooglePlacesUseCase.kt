package gr.dipae.googlemapsearcher.usecases

import gr.dipae.googlemapsearcher.data.MapSearcherRepository
import gr.dipae.googlemapsearcher.model.GooglePlaces
import timber.log.Timber
import javax.inject.Inject

class GetGooglePlacesUseCase @Inject constructor(
    private val repository: MapSearcherRepository
) {
    suspend operator fun invoke(location: String, query: String): GooglePlaces {
        return try {
            repository.getGooglePlaces(location, query)
        } catch (ex: Exception) {
            Timber.tag(GetGooglePlacesUseCase::class.simpleName).e(ex)
            GooglePlaces()
        }
    }
}