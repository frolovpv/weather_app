package com.cappasity.weatherapp.presentation.fragment

import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.cappasity.weatherapp.R
import com.cappasity.weatherapp.databinding.FragmentMainScreenBinding
import com.cappasity.weatherapp.di.main.MainInjector
import com.cappasity.weatherapp.domain.extension.createViewModel
import com.cappasity.weatherapp.domain.extension.onClick
import com.cappasity.weatherapp.presentation.adapter.CitiesWeatherAdapter
import com.cappasity.weatherapp.presentation.base.VMBaseFragment
import com.cappasity.weatherapp.presentation.viewmodel.MainViewModel

class MainScreenFragment: VMBaseFragment<MainViewModel>(R.layout.fragment_main_screen) {

    override val binding: FragmentMainScreenBinding by viewBinding(FragmentMainScreenBinding::bind)

    override fun provideViewModel(): MainViewModel = createViewModel {
        MainInjector.mainComponent.mainViewModel
    }

    override fun initView() = binding.apply {
        citiesBtn.onClick {
            navigateTo(MainScreenFragmentDirections.actionMainScreenFragmentToCitiesListFragment())
        }
    }

    override fun setObservers(viewOwner: LifecycleOwner) {
        super.setObservers(viewOwner)
        CitiesWeatherAdapter(this@MainScreenFragment,viewModel.citiesState.value!!.size).apply {
            binding.citiesVP.adapter = this
        }
        binding.citiesVP.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.pageIndicator.updatePosition(position)

            }
        })
        binding.pageIndicator.updatePosition(0)
        binding.pageIndicator.mDotsCount = viewModel.citiesState.value!!.size
    }

}