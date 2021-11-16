package com.gustavohisan.apelieuser.domain.model.address

sealed class GetAddressFromCepState {

    data class Success(val cepAddress: CepAddress?) : GetAddressFromCepState()

    object Empty : GetAddressFromCepState()

    object Error : GetAddressFromCepState()
}
