package com.accenture.marvel.presentation.hq

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.accenture.marvel.model.Hq
import com.accenture.marvel.util.Extra

class HqViewModel : ViewModel() {

    private val _hq by lazy {
        MutableLiveData<Hq>()
    }

    val hq : LiveData<Hq>
        get() = _hq

    fun getHq(extras: Bundle?) {
        extras?.also {
            val hqq = it[Extra.HQ.value] as? Hq
            _hq.postValue(hqq)
//                val price = controller.formatPrices(it.price)
//                it.title, it.description, price, it.coverUrl
        }
    }
}