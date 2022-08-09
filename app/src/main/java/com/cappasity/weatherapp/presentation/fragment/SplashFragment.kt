package com.cappasity.weatherapp.presentation.fragment

import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import by.kirich1409.viewbindingdelegate.viewBinding
import com.cappasity.weatherapp.R
import com.cappasity.weatherapp.databinding.FragmentSplashBinding
import com.cappasity.weatherapp.di.main.MainInjector
import com.cappasity.weatherapp.domain.extension.createViewModel
import com.cappasity.weatherapp.presentation.base.VMBaseFragment
import com.cappasity.weatherapp.presentation.viewmodel.MainViewModel

class SplashFragment: VMBaseFragment<MainViewModel>(R.layout.fragment_splash) {

    override val binding: FragmentSplashBinding by viewBinding(FragmentSplashBinding::bind)

    override fun provideViewModel(): MainViewModel = createViewModel {
       MainInjector.mainComponent.mainViewModel
    }

    override fun setObservers(viewOwner: LifecycleOwner) {
        super.setObservers(viewOwner)
        if (!internetIsConnected() && viewModel.hasNotCities()){
            Toast.makeText(
                requireContext(),
                "Сервер недоступен, проверьте доступ в интернет. Повторите попытку позже",
                Toast.LENGTH_LONG
            ).show()
        } else {
            viewModel.refreshCitiesList()
            viewModel.loadWeatherData() {
                navigateTo(SplashFragmentDirections.actionSplashFragmentToMainScreenFragment())
            }
        }
    }

    private fun internetIsConnected(): Boolean {
        return try {
            val command = "ping -c 1 google.com"
            Runtime.getRuntime().exec(command).waitFor() == 0
        } catch (e: Exception) {
            false
        }
    }

}