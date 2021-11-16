package com.gustavohisan.apelieuser.address.model

internal sealed class GetAddressFromCepState {

    data class Success(val cepAddress: CepAddress?) : GetAddressFromCepState()

    object Empty : GetAddressFromCepState()

    object Error : GetAddressFromCepState()

    object None : GetAddressFromCepState()
}
