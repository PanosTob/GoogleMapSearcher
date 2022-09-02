package gr.dipae.googlemapsearcher.usecases

import gr.dipae.googlemapsearcher.data.MapSearcherRepository
import javax.inject.Inject

class IsLocationServiceEnabledUseCase @Inject constructor(
    private val repository: MapSearcherRepository
) {
    operator fun invoke(): Boolean {
        return repository.isLocationServiceEnabled()
    }
}