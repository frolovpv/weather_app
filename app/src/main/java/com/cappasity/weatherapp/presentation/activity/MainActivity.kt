package com.cappasity.weatherapp.presentation.activity

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.cappasity.weatherapp.R
import com.cappasity.weatherapp.databinding.MainActivityBinding
import com.cappasity.weatherapp.di.main.MainInjector
import com.cappasity.weatherapp.domain.extension.createActivityViewModel
import com.cappasity.weatherapp.presentation.base.BaseActivity
import com.cappasity.weatherapp.presentation.viewmodel.MainViewModel

class MainActivity: BaseActivity<MainViewModel>() {

    lateinit var binding: MainActivityBinding

    override fun provideViewModel(): MainViewModel = createActivityViewModel {
        MainInjector.mainComponent.mainViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    private fun findChildController(): NavController? {
        return (supportFragmentManager.findFragmentById(R.id.main_fragment_container) as? NavHostFragment)?.navController
    }

    override fun onBackPressed() {
//        findChildController()?.let{
//            when(it.currentDestination?.id){
//                R.id.mainScreenFragment -> this.finish()
//                else -> super.onBackPressed()
//            }
//        }

    }

}