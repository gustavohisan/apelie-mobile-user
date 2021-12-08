package com.gustavohisan.apelieuser.orders.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gustavohisan.apelieuser.domain.usecase.order.LoadOrder
import com.gustavohisan.apelieuser.domain.usecase.order.RateOrder
import com.gustavohisan.apelieuser.orders.mapper.GetOrderByIdStateMapper
import com.gustavohisan.apelieuser.orders.model.GetOrderByIdState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class OrderViewModel(
    private val loadOrder: LoadOrder,
    private val getOrderByIdStateMapper: GetOrderByIdStateMapper,
    private val rateOrder: RateOrder
) : ViewModel() {

    private val _getOrderState = MutableLiveData<GetOrderByIdState>()
    val getOrderState: LiveData<GetOrderByIdState> = _getOrderState

    private val _rateOrderResult = MutableLiveData<Boolean>()
    val rateOrderResult: LiveData<Boolean> = _rateOrderResult

    fun loadOrderById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _getOrderState.postValue(getOrderByIdStateMapper.toPresentation(loadOrder(id)))
        }
    }

    fun rateFinishedOrder(id: Int, rating: Int, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _rateOrderResult.postValue(rateOrder(id, description, rating))
        }
    }
}
