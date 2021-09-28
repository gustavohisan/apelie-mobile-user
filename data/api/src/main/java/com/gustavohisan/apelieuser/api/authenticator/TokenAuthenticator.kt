package com.gustavohisan.apelieuser.api.authenticator

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

internal class TokenAuthenticator : Authenticator {

    private var token = ""

    fun setToken(value: String) {
        token = value
    }

    override fun authenticate(route: Route?, response: Response): Request? {
        return response.request().newBuilder()
            .header("Authorization", token)
            .build()
    }
}
