package com.cappasity.weatherapp.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import androidx.core.widget.doOnTextChanged
import com.cappasity.weatherapp.databinding.ViewSearchBinding
import com.cappasity.weatherapp.domain.extension.clearFocusAndHideKeyboard
import com.cappasity.weatherapp.domain.extension.onClick
import com.cappasity.weatherapp.domain.extension.requestFocusAndShowKeyboard

class SearchView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    val binding = ViewSearchBinding.inflate(LayoutInflater.from(context), this)

    val query get() = binding.searchInput.text.toString()

    var onStartSearch: ((String) -> Unit)? = null
    var onTextChanged: ((String) -> Unit)? = null

    init {
        orientation = HORIZONTAL

        binding.searchClear.visibility = View.GONE
        binding.root.onClick{
            if (binding.searchInput.isFocused) {
                binding.searchInput.clearFocus()
                dispatchStartSearch()
            } else {
                binding.searchInput.requestFocusAndShowKeyboard()
            }
        }
        binding.searchClear.onClick {
            clearSearch()
        }
        binding.searchInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                dispatchStartSearch()
                true
            } else {
                false
            }
        }
        binding.searchInput.doOnTextChanged { text, _, _, _ ->
            onTextChanged?.invoke(query)
            binding.searchClear.visibility = if (text != null && text.isNotEmpty())
                View.VISIBLE else View.GONE
        }
    }

    fun clearSearch(){
        binding.searchInput.setText("")
        binding.searchInput.clearFocusAndHideKeyboard()
        dispatchStartSearch()
    }


    private fun dispatchStartSearch() {
        onStartSearch?.invoke(query)
    }

}