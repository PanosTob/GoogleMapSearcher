package gr.dipae.googlemapsearcher.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GooglePlacesResponse(
    val results: List<GooglePlacesDetailsResponse>?
)

@JsonClass(generateAdapter = true)
data class GooglePlacesDetailsResponse(
    @Json(name = "place_id")
    val placeId: String?,
    val geometry: GooglePlacesLocationResponse?
)

@JsonClass(generateAdapter = true)
data class GooglePlacesLocationResponse(
    val location: GooglePlaceCoordinatesResponse?
)

@JsonClass(generateAdapter = true)
data class GooglePlaceCoordinatesResponse(
    val lat: Double?,
    val lng: Double?
)