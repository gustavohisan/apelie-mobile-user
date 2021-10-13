package com.gustavohisan.apelieuser.product.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gustavohisan.apelieuser.domain.usecase.product.LoadProductData
import com.gustavohisan.apelieuser.product.mapper.ProductStateMapper
import com.gustavohisan.apelieuser.product.model.ProductState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class ProductViewModel(
    private val loadProductData: LoadProductData,
    private val productStateMapper: ProductStateMapper
) : ViewModel() {

    private val _productState: MutableLiveData<ProductState> = MutableLiveData()
    val productState: LiveData<ProductState>
        get() = _productState

    fun getProductData(productId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _productState.postValue(productStateMapper.toPresentation(loadProductData(productId)))
        }
    }
}
