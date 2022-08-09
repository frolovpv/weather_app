package com.cappasity.weatherapp.presentation.component

import android.view.View

class OnClick : View.OnClickListener {

    private val onClickListener: View.OnClickListener

    constructor(listener: View.OnClickListener) {
        onClickListener = listener
    }

    constructor(listener: (View) -> Unit) {
        onClickListener = View.OnClickListener { listener.invoke(it) }
    }

    override fun onClick(v: View) {
        System.currentTimeMillis().apply {
            if (this >= previousClickTimeMillis + DELAY_MILLIS) {
                previousClickTimeMillis = this
                onClickListener.onClick(v)
            }
        }

    }

    companion object {
        private const val DELAY_MILLIS = 200L

        private var previousClickTimeMillis = 0L
    }


}