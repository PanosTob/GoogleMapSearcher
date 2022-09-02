package gr.dipae.googlemapsearcher.data

import gr.dipae.googlemapsearcher.model.GooglePlacesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GooglePlacesApi {
    @GET("/maps/api/place/textsearch/json?radius=5000&key=AIzaSyAxdaDkuLvnbcZTmgc5BfnIrDiqNlskQuY")
    suspend fun getGooglePlaces(
        @Query("location") location: String,
        @Query("query") query: String
    ): Response<GooglePlacesResponse>
}