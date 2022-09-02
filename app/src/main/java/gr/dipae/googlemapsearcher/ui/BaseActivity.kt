package gr.dipae.googlemapsearcher.ui

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import gr.dipae.googlemapsearcher.util.ConnectivityStatus
import gr.dipae.googlemapsearcher.R

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: VB

    private var snackbar: Snackbar? = null

    abstract fun getViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
    }

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration?) {
        if (overrideConfiguration != null) {
            val uiMode = overrideConfiguration.uiMode
            overrideConfiguration.setTo(baseContext.resources.configuration)
            overrideConfiguration.uiMode = uiMode
        }
        super.applyOverrideConfiguration(overrideConfiguration)
    }

    protected fun onConnectivityChange(connectivityStatus: ConnectivityStatus) {
        if (snackbar == null) {
            snackbar = Snackbar.make(binding.root, R.string.error_no_internet, Snackbar.LENGTH_INDEFINITE)
        }
        when (connectivityStatus) {
            ConnectivityStatus.CONNECTED -> {
                snackbar?.dismiss()
            }
            ConnectivityStatus.DISCONNECTED -> {
                snackbar?.show()
            }
        }
    }
}