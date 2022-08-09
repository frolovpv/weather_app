package com.cappasity.weatherapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexandrius.accordionswipelayout.library.SwipeLayout
import com.cappasity.weatherapp.R
import com.cappasity.weatherapp.domain.extension.onClick
import com.cappasity.weatherapp.presentation.component.RVItemClickListener
import com.cappasity.weatherapp.presentation.model.PWeatherModel

class CitiesSuggestionListAdapter: RecyclerView.Adapter<CitiesSuggestionListAdapter.CitiesSuggestionListViewHolder>() {
    private var mItemInteractListener: RVItemClickListener? = null

    fun setOnItemInteractListener(listener: RVItemClickListener) {
        this.mItemInteractListener = listener
    }

    private var citiesList: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesSuggestionListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.vh_city_suggestion_list_item, parent, false)
        return CitiesSuggestionListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CitiesSuggestionListViewHolder, position: Int) {
        holder.onBind(citiesList[position], position)
    }

    override fun getItemCount(): Int {
        return citiesList.size
    }

    fun refreshList(list: MutableList<String>) {
        citiesList.clear()
        citiesList.addAll(list)
        notifyDataSetChanged()
    }

    inner class CitiesSuggestionListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cityName: TextView = itemView.findViewById(R.id.cityName)

        fun onBind(city: String, position: Int) {
            cityName.text = city
            itemView.rootView.onClick {
                if (mItemInteractListener != null) {
                    mItemInteractListener?.onItemClick(itemView, position, PWeatherModel(0L,city))
                }
            }
        }

    }
}
