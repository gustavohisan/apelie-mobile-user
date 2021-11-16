package com.gustavohisan.apelieuser.repository.model.address

sealed class GetAddressFromCepState {

    data class Success(val cepAddress: CepAddress?) : GetAddressFromCepState()

    object Error : GetAddressFromCepState()
}
