package com.cappasity.weatherapp.presentation.fragment

import android.view.View
import androidx.lifecycle.LifecycleOwner
import by.kirich1409.viewbindingdelegate.viewBinding
import com.cappasity.weatherapp.R
import com.cappasity.weatherapp.data.local.database.entity.CityWeatherEntity
import com.cappasity.weatherapp.databinding.FragmentCitiesBinding
import com.cappasity.weatherapp.di.main.MainInjector
import com.cappasity.weatherapp.domain.extension.createViewModel
import com.cappasity.weatherapp.presentation.adapter.CitiesListAdapter
import com.cappasity.weatherapp.presentation.adapter.CitiesSuggestionListAdapter
import com.cappasity.weatherapp.presentation.base.VMBaseFragment
import com.cappasity.weatherapp.presentation.component.RVItemClickListener
import com.cappasity.weatherapp.presentation.model.PWeatherModel
import com.cappasity.weatherapp.presentation.viewmodel.MainViewModel

class CitiesListFragment: VMBaseFragment<MainViewModel>(R.layout.fragment_cities) {

    override val binding: FragmentCitiesBinding by viewBinding(FragmentCitiesBinding::bind)

    override fun provideViewModel(): MainViewModel = createViewModel {
        MainInjector.mainComponent.mainViewModel
    }
    private val citiesListAdapter = CitiesListAdapter()
    private val citiesSuggestionListAdapter = CitiesSuggestionListAdapter()


    override fun initView() = binding.apply{
        citiesListRV.adapter = citiesListAdapter
        suggestionsRV.adapter = citiesSuggestionListAdapter

        citiesListAdapter.setOnItemInteractListener(object: RVItemClickListener {
            override fun onItemClick(view: View, position: Int, model: PWeatherModel) {
                viewModel.loadWeatherData(){
                    navigateTo(CitiesListFragmentDirections.actionCitiesFragmentToMainScreenFragment())
                }
            }

            override fun onDeleteClick(view: View, position: Int, model: PWeatherModel) {
                viewModel.deleteCity(model.id)
                viewModel.refreshCitiesList()
            }
        })
        citiesSuggestionListAdapter.setOnItemInteractListener(object: RVItemClickListener {
            override fun onItemClick(view: View, position: Int, model: PWeatherModel) {
                viewModel.querySuggestions.value?.get(position)?.let {
                    viewModel.addCity(
                        CityWeatherEntity(
                            null,
                            0L,
                            it.featureName,
                            it.longitude,
                            it.latitude,
                        )
                    )
                }
                viewModel.refreshCitiesList()
                etCityContainer.clearSearch()
            }
            override fun onDeleteClick(view: View, position: Int, model: PWeatherModel) {}
        })

        etCityContainer.onTextChanged = { query ->
            if (query.isEmpty()) {
                viewModel.querySuggestions.postValue(listOf())
            }
            viewModel.findCity(query)
        }
        etCityContainer.onStartSearch = { query ->
            viewModel.findCity(query)
        }
    }


    override fun setObservers(viewOwner: LifecycleOwner) {
        super.setObservers(viewOwner)
        loadActualCities()
        viewModel.citiesState.observe(viewOwner){
            loadActualCities()
        }
        viewModel.querySuggestions.observe(viewOwner){
            if (it.isEmpty())
                binding.suggestionsRV.visibility = View.GONE
            else {
                binding.suggestionsRV.visibility = View.VISIBLE
                mutableListOf<String>().apply {
                    viewModel.querySuggestions.value?.forEach {
                        this.add(it.featureName)
                    }
                    citiesSuggestionListAdapter.refreshList(this)
                }
            }
        }
    }


    private fun loadActualCities(){
        mutableListOf<PWeatherModel>().apply {
            viewModel.citiesState.value?.forEach {
                this.add(PWeatherModel(it.id ?: 0L,it.name ?: ""))
            }
            citiesListAdapter.refreshList(this)
        }
    }

}