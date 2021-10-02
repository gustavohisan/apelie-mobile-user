package com.gustavohisan.apelieuser.product.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gustavohisan.apelieuser.product.model.ProductState

internal class ProductViewModel {

    private val _productState: MutableLiveData<ProductState> = MutableLiveData()
    val productState: LiveData<ProductState>
        get() = _productState
}
