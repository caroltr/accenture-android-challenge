package com.accenture.marvel.presentation.hq

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.accenture.marvel.data.model.Price
import com.accenture.marvel.presentation.character.model.Hq
import com.accenture.marvel.presentation.hq.model.HqPresentation
import com.accenture.marvel.util.Extra
import com.accenture.marvel.util.asReais
import javax.inject.Inject

class HqViewModel @Inject constructor(): ViewModel() {

    private val _hq by lazy {
        MutableLiveData<HqPresentation>()
    }

    val hq : LiveData<HqPresentation>
        get() = _hq

    fun getHq(extras: Bundle?) {
        extras?.also { bundle ->
            (bundle[Extra.HQ.value] as? Hq)?.also {
                val price = formatPrices(it.price)
                val hqPresentation = HqPresentation(it.title, it.description, it.coverUrl, price)
                _hq.postValue(hqPresentation)
            }
        }
    }

    private fun formatPrices(prices: List<Price>): String {
        return prices.sortedBy { it.price }.joinToString(" / ") { i -> i.price.asReais() }
    }
}