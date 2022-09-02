package gr.dipae.googlemapsearcher.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GooglePlacesResponse(
    @Json(name = "results")
    val results: List<GooglePlacesDetailsResponse>?
)

@JsonClass(generateAdapter = true)
data class GooglePlacesDetailsResponse(
    @Json(name = "place_id")
    val placeId: String?,
    @Json(name = "geometry")
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