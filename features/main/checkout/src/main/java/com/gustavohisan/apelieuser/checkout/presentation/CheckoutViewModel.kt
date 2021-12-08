package com.gustavohisan.apelieuser.checkout.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gustavohisan.apelieuser.checkout.mapper.CheckoutItemsFromCartStateMapper
import com.gustavohisan.apelieuser.checkout.mapper.GetAddressStateMapper
import com.gustavohisan.apelieuser.checkout.model.CheckoutItemsFromCartState
import com.gustavohisan.apelieuser.checkout.model.GetUserAddressesState
import com.gustavohisan.apelieuser.domain.usecase.address.GetUserAddressList
import com.gustavohisan.apelieuser.domain.usecase.cart.CheckoutItemsFromCart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

internal class CheckoutViewModel(
    private val getUserAddressList: GetUserAddressList,
    private val getAddressStateMapper: GetAddressStateMapper,
    private val checkoutItemsFromCart: CheckoutItemsFromCart,
    private val checkoutItemsFromCartStateMapper: CheckoutItemsFromCartStateMapper
) : ViewModel() {

    private val _getUserAddressesState: MutableLiveData<GetUserAddressesState> = MutableLiveData()
    val getUserAddressesState: LiveData<GetUserAddressesState>
        get() = _getUserAddressesState

    private val _checkoutCartState: MutableLiveData<CheckoutItemsFromCartState> = MutableLiveData()
    val checkoutCartState: LiveData<CheckoutItemsFromCartState>
        get() = _checkoutCartState

    fun getUserAddressList() {
        Timber.d("getUserAddresses")
        viewModelScope.launch(Dispatchers.IO) {
            _getUserAddressesState.postValue(
                getAddressStateMapper.toPresentation(getUserAddressList.invoke())
            )
        }
    }

    fun finishCheckout(addressId: Int, paymentMethod: String) {
        Timber.d("finishCheckout - addressId = $addressId - paymentMethod = $paymentMethod")
        viewModelScope.launch(Dispatchers.IO) {
            _checkoutCartState.postValue(
                checkoutItemsFromCartStateMapper.toPresentation(
                    checkoutItemsFromCart(
                        paymentMethod,
                        addressId
                    )
                )
            )
        }
    }

    fun resetState() {
        _checkoutCartState.value = CheckoutItemsFromCartState.None
    }
}
