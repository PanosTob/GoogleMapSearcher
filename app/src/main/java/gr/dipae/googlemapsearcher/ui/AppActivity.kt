package gr.dipae.googlemapsearcher.ui

import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import gr.dipae.googlemapsearcher.util.AMTH_FOREGROUND_LOCATION_PERMISSIONS
import gr.dipae.googlemapsearcher.databinding.ActivityAppBinding
import gr.dipae.googlemapsearcher.util.hasPermissions

@AndroidEntryPoint
class AppActivity : BaseActivity<ActivityAppBinding>() {

    private val viewModel: AppViewModel by viewModels()

    override fun getViewBinding(): ActivityAppBinding = ActivityAppBinding.inflate(layoutInflater)

    fun checkForLocationServices() {
        if (hasPermissions(AMTH_FOREGROUND_LOCATION_PERMISSIONS)) {
            viewModel.showLocationOnMap()
            return
        }
    }
}