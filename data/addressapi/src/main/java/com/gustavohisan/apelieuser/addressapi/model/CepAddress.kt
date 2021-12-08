package com.gustavohisan.apelieuser.addressapi.model

import com.google.gson.annotations.SerializedName

internal data class CepAddress(
    @SerializedName("logradouro")
    val street: String?,
    @SerializedName("bairro")
    val district: String?,
    @SerializedName("localidade")
    val city: String?,
    @SerializedName("uf")
    val state: String?
)
