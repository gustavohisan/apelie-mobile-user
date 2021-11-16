package com.gustavohisan.apelieuser.addressapi.factory

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class ApiFactory {

    private val retrofit = Retrofit.Builder()
        .baseUrl(API_URL)
        .client(getOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()

    /**
     * Returns the Retrofit instance of the class.
     *
     * @return a [Retrofit] built with the API url
     */
    fun getRetrofitInstance(): Retrofit = retrofit

    private fun getOkHttpClient(): OkHttpClient =
        OkHttpClient()
            .newBuilder()
            .build()

    private companion object {

        private const val API_URL = "https://viacep.com.br/ws/"
    }
}
