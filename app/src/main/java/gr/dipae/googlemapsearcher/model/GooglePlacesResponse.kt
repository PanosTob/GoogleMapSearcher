package gr.dipae.googlemapsearcher.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GooglePlacesResponse(
    @Json(name = "html_attributions")
    val htmlAttributions: List<String>?,
    @Json(name = "results")
    val candidates: List<GooglePlacesDetailsResponse>?,
    @Json(name = "status")
    val status: String?,
)

@JsonClass(generateAdapter = true)
data class GooglePlacesDetailsResponse(
    @Json(name = "name")
    val name: String?,
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