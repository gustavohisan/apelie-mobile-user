package com.gustavohisan.apelieuser.product.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gustavohisan.apelieuser.domain.usecase.cart.InsertProductInCart
import com.gustavohisan.apelieuser.domain.usecase.product.LoadProductData
import com.gustavohisan.apelieuser.product.mapper.InsertProductInCartStateMapper
import com.gustavohisan.apelieuser.product.mapper.ProductStateMapper
import com.gustavohisan.apelieuser.product.model.InsertProductInCartState
import com.gustavohisan.apelieuser.product.model.ProductState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class ProductViewModel(
    private val loadProductData: LoadProductData,
    private val productStateMapper: ProductStateMapper,
    private val insertProductInCart: InsertProductInCart,
    private val insertProductInCartStateMapper: InsertProductInCartStateMapper
) : ViewModel() {

    private val _productState: MutableLiveData<ProductState> = MutableLiveData()
    val productState: LiveData<ProductState>
        get() = _productState

    private val _insertProductInCartState: MutableLiveData<InsertProductInCartState> =
        MutableLiveData()
    val insertProductInCartState: LiveData<InsertProductInCartState>
        get() = _insertProductInCartState

    fun getProductData(productId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _productState.postValue(productStateMapper.toPresentation(loadProductData(productId)))
        }
    }

    fun addProductToCart(productId: Int, description: String, quantity: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _insertProductInCartState.postValue(
                insertProductInCartStateMapper.toPresentation(
                    insertProductInCart(productId, description, quantity)
                )
            )
        }
    }
}
