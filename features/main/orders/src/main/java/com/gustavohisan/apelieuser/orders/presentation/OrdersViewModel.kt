package com.gustavohisan.apelieuser.orders.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gustavohisan.apelieuser.domain.usecase.order.LoadUserOrders
import com.gustavohisan.apelieuser.orders.mapper.GetUserOrdersStateMapper
import com.gustavohisan.apelieuser.orders.model.GetUserOrdersState
import com.gustavohisan.apelieuser.orders.model.Order
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class OrdersViewModel(
    private val loadUserOrders: LoadUserOrders,
    private val getUserOrdersStateMapper: GetUserOrdersStateMapper
) : ViewModel() {

    private val _getUserOrdersState = MutableLiveData<GetUserOrdersState>()
    val getUserOrdersState: LiveData<GetUserOrdersState> = _getUserOrdersState

    fun loadOrders() {
        viewModelScope.launch(Dispatchers.IO) {
            _getUserOrdersState.postValue(getUserOrdersStateMapper.toPresentation(loadUserOrders()))
        }
    }

    fun getGroupedOrders(orders: List<Order>) =
        orders.groupBy { it.createdAt.substring(0, 8) }.mapKeys {
            "${
                when (it.key.subSequence(5, 7)) {
                    "01" -> "Janeiro"
                    "02" -> "Fevereiro"
                    "03" -> "Março"
                    "04" -> "Abril"
                    "05" -> "Maio"
                    "06" -> "Junho"
                    "07" -> "Julho"
                    "08" -> "Agosto"
                    "09" -> "Setembro"
                    "10" -> "Outubro"
                    "11" -> "Novembro"
                    "12" -> "Dezembro"
                    else -> "Erro"
                }
            } de ${it.key.subSequence(0, 4)}"
        }
}
