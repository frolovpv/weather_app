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

class CitiesListAdapter: RecyclerView.Adapter<CitiesListAdapter.CitiesListViewHolder>() {
    private var mItemInteractListener: RVItemClickListener? = null

    fun setOnItemInteractListener(listener: RVItemClickListener) {
        this.mItemInteractListener = listener
    }

    private var citiesList: MutableList<PWeatherModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.vh_city_list_item, parent, false)
        return CitiesListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CitiesListViewHolder, position: Int) {
        holder.onBind(citiesList[position], position)
    }

    override fun getItemCount(): Int {
        return citiesList.size
    }

    fun refreshList(list: MutableList<PWeatherModel>) {
        citiesList.clear()
        citiesList.addAll(list)
        notifyDataSetChanged()
    }

    inner class CitiesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cityName: TextView = itemView.findViewById(R.id.cityName)
        private val swipeLayout: SwipeLayout = itemView.findViewById(R.id.swipe_layout)

        fun onBind(model: PWeatherModel, position: Int) {
            cityName.text = model.cityName
            swipeLayout.onClick {
                if (mItemInteractListener != null) {
                    mItemInteractListener?.onItemClick(itemView, position,model)
                }
            }
            swipeLayout.setOnSwipeItemClickListener { left, index ->
                if (!left && index == 0) {
                    if (mItemInteractListener != null) {
                        mItemInteractListener?.onDeleteClick(itemView, position,model)
                    }
                }
            }
        }

    }
}

