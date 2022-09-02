package gr.dipae.googlemapsearcher.model

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import java.io.Serializable

data class MapClusterItem(
    val id: String,
    val latitude: Double,
    val longitude: Double,
) : ClusterItem, Serializable {
    override fun getSnippet(): String = ""

    override fun getTitle(): String = id

    override fun getPosition(): LatLng = LatLng(latitude, longitude)
}