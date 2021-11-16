package com.gustavohisan.apelieuser.address.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gustavohisan.apelieuser.address.mapper.GetAddressFromCepStateMapper
import com.gustavohisan.apelieuser.address.mapper.InsertAddressStateMapper
import com.gustavohisan.apelieuser.address.model.GetAddressFromCepState
import com.gustavohisan.apelieuser.address.model.InsertAddressState
import com.gustavohisan.apelieuser.domain.usecase.address.InsertAddress
import com.gustavohisan.apelieuser.domain.usecase.address.SearchAddressByCep
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

internal class AddressViewModel(
    private val getAddressByCep: SearchAddressByCep,
    private val insertAddress: InsertAddress,
    private val getAddressFromCepStateMapper: GetAddressFromCepStateMapper,
    private val insertAddressStateMapper: InsertAddressStateMapper
) : ViewModel() {

    private val _insertAddressState: MutableLiveData<InsertAddressState> = MutableLiveData()
    val insertAddressState: LiveData<InsertAddressState>
        get() = _insertAddressState

    private val _getAddressByCepState: MutableLiveData<GetAddressFromCepState> = MutableLiveData()
    val getAddressByCepState: LiveData<GetAddressFromCepState>
        get() = _getAddressByCepState

    fun insertAddress(
        city: String,
        complement: String,
        district: String,
        number: String,
        state: String,
        street: String,
        zipCode: String
    ) {
        Timber.d("insertAddress")
        viewModelScope.launch(Dispatchers.IO) {
            _insertAddressState.postValue(
                insertAddressStateMapper.toPresentation(
                    insertAddress.invoke(
                        city,
                        complement,
                        district,
                        number,
                        state,
                        street,
                        zipCode
                    )
                )
            )
        }
    }

    fun getAddressByCep(cep: String) {
        Timber.d("getAddressByCep - cep = $cep")
        viewModelScope.launch(Dispatchers.IO) {
            _getAddressByCepState.postValue(
                getAddressFromCepStateMapper.toPresentation(
                    getAddressByCep.invoke(cep)
                )
            )
        }
    }
}
