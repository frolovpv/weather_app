package com.cappasity.weatherapp.domain.extension

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import com.cappasity.weatherapp.presentation.component.OnClick


// Слушатели для клика, не позволяющие быстро дважды кликнуть на одну и ту же кнопку
fun View.onClick(listener: View.OnClickListener) {
    setOnClickListener(OnClick(listener))
}

fun View.onClick(listener: (View) -> Unit) {
    setOnClickListener(OnClick(listener))
}

fun View.requestFocusAndShowKeyboard() {
    requestFocus()
    post {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun View.clearFocusAndHideKeyboard() {

    fun hideKeyboard() {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(this.windowToken, 0)
    }

    clearFocus()
    hideKeyboard()
    postDelayed({
        hideKeyboard()
    }, 150)
}

fun View.dpToPx(@DimenRes dimRes: Int): Float {
    return resources.getDimension(dimRes)
}

@ColorInt
fun View.getColor(@ColorRes colorRes: Int): Int {
    return context.resources.getColor(colorRes)
}