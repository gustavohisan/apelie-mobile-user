package com.gustavohisan.apelieuser.api.factory

import com.google.gson.GsonBuilder
import com.gustavohisan.apelieuser.api.interceptor.NetworkInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Factory used to retrieve the API instance available.
 *
 * @param networkInterceptor interceptor used to handle network issues
 */
internal class ApiFactory(private val networkInterceptor: NetworkInterceptor) {

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
            .addInterceptor(networkInterceptor)
            .build()

    private companion object {

        private const val API_URL =
            "https://shrouded-springs-20631.herokuapp.com/api/"
    }
}
