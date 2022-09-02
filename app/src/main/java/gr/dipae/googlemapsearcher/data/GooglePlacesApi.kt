package gr.dipae.googlemapsearcher.data

import gr.dipae.googlemapsearcher.model.GooglePlacesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GooglePlacesApi {
    @GET("/maps/api/place/textsearch/output_type?query={query}&location=latitude,longitude&radius=5000")
    suspend fun getGooglePlaces(@Path("query") query: String): Response<GooglePlacesResponse>
}