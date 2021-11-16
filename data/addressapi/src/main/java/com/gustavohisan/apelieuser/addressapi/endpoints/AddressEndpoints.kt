package com.gustavohisan.apelieuser.addressapi.endpoints

import com.gustavohisan.apelieuser.addressapi.model.CepAddress
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

internal interface AddressEndpoints {

    @GET("{cep}/json")
    suspend fun getAddressByCep(@Path("cep") cep: String): Response<CepAddress>
}
