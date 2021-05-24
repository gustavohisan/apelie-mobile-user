package com.gustavohisan.apelieuser.api.provider

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Provider used to retrieve the API instance available.
 */
internal class ApiProvider {

    private val retrofit = Retrofit.Builder()
        .baseUrl(API_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()

    /**
     * Returns the Retrofit instance of the class.
     *
     * @return a [Retrofit] built with the API url
     */
    fun getRetrofitInstance(): Retrofit = retrofit

    private companion object {

        private const val API_URL =
            "http://ec2-54-232-70-145.sa-east-1.compute.amazonaws.com:8080/api/"
    }
}
