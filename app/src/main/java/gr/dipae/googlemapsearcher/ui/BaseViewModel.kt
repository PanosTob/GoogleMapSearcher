package gr.dipae.googlemapsearcher.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gr.dipae.googlemapsearcher.*
import gr.dipae.googlemapsearcher.util.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @Inject
    lateinit var errorLiveData: ErrorLiveData

    val connectivityUI = SingleLiveEvent<ConnectivityStatus>()

    protected fun launch(delayValue: Long = 0, function: suspend () -> Unit) {
        viewModelScope.launch {
            delay(delayValue)
            try {
                function.invoke()
            } catch (exception: NoInternetException) {
                errorLiveData.value = R.string.error_no_internet
            }
        }
    }

    protected fun launchWithProgress(
        delayValue: Long = 0,
        preload: suspend () -> Unit = {},
        function: suspend () -> Unit
    ) {
        viewModelScope.launch {
            delay(delayValue)
            preload.invoke()
            LoadingLiveData.postValue(true)
            try {
                function.invoke()
            } catch (exception: NoInternetException) {
                errorLiveData.value = R.string.error_no_internet
            }
        }.apply {
            invokeOnCompletion {
                LoadingLiveData.postValue(false)
            }
        }
    }
}