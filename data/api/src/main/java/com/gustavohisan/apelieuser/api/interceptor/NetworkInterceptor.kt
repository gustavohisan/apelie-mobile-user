package com.gustavohisan.apelieuser.api.interceptor

import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody

/**
 * Interceptor used to handle network connection issues.
 */
internal class NetworkInterceptor : Interceptor {

    /**
     * Intercepts the request, if the request fails returns an response with an network error code,
     * indicating that the server was unavailable or the connection timed out.
     *
     * @param chain default param for intercept function
     *
     * @return the [Response] of the request, if failed a custom [Response] indicating the network
     * error.
     */
    override fun intercept(chain: Interceptor.Chain): Response =
        try {
            val request = chain.request()
            chain.proceed(request)
        } catch (e: Exception) {
            Response.Builder()
                .code(503)
                .protocol(Protocol.HTTP_2)
                .body(ResponseBody.create(null, "empty body"))
                .message("Connection failed")
                .request(chain.request())
                .build()
        }
}
