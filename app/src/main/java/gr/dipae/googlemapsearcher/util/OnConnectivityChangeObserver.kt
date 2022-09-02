package gr.dipae.googlemapsearcher.util

interface OnConnectivityChangeObserver {

    fun subscribeOnChanges(callback: OnConnectivityChangeCallback)

    fun unsubscribe()
}