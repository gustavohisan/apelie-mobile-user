package com.gustavohisan.apelieuser.addressapi.model

internal sealed class GetAddressFromCepState {

    data class Success(val cepAddress: CepAddress?) : GetAddressFromCepState()

    object Error : GetAddressFromCepState()
}
