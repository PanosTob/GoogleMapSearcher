package gr.dipae.googlemapsearcher.data

import gr.dipae.googlemapsearcher.model.GooglePlacesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GooglePlacesApi {
    @GET("/maps/api/place/findplacefromtext/json?fields=name,geometry&inputtype=textquery&key=AIzaSyAjchHbl-h_S0gggA8N1PurYmqOSeCB-hw")
    suspend fun getGooglePlaces(
        @Query("locationbias") location: String,
        @Query("input") query: String
    ): Response<GooglePlacesResponse>
}