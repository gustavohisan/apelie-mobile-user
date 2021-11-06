package com.gustavohisan.apelieuser.cart.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gustavohisan.apelieuser.cart.mapper.EditProductInCartStateMapper
import com.gustavohisan.apelieuser.cart.mapper.GetItemsFromCartStateMapper
import com.gustavohisan.apelieuser.cart.model.EditProductInCartState
import com.gustavohisan.apelieuser.cart.model.GetItemsFromCartState
import com.gustavohisan.apelieuser.domain.usecase.cart.EditProductInCart
import com.gustavohisan.apelieuser.domain.usecase.cart.GetCartItemsFromUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

internal class CartViewModel(
    private val getCartItemsFromUser: GetCartItemsFromUser,
    private val getItemsFromCartStateMapper: GetItemsFromCartStateMapper,
    private val editProductInCart: EditProductInCart,
    private val editProductInCartStateMapper: EditProductInCartStateMapper
) : ViewModel() {

    private val _getItemFromCartState: MutableLiveData<GetItemsFromCartState> = MutableLiveData()
    val getItemsFromCartState: LiveData<GetItemsFromCartState>
        get() = _getItemFromCartState

    private val _editCartItem: MutableLiveData<EditProductInCartState> = MutableLiveData()
    val editCartItem: LiveData<EditProductInCartState>
        get() = _editCartItem

    fun getCartItems() {
        Timber.d("getCartItems")
        viewModelScope.launch(Dispatchers.IO) {
            _getItemFromCartState.postValue(
                getItemsFromCartStateMapper.toPresentation(
                    getCartItemsFromUser()
                )
            )
        }
    }

    fun editCartItem(id: Int, quantity: Int, description: String) {
        Timber.d("editCartItem - id = $id - quantity = $quantity - description = $description")
        viewModelScope.launch(Dispatchers.IO) {
            _editCartItem.postValue(
                editProductInCartStateMapper.toPresentation(
                    editProductInCart(id, description, quantity)
                )
            )
        }
    }
}
