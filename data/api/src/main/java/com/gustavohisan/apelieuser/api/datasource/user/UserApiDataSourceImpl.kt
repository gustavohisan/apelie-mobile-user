package com.gustavohisan.apelieuser.api.datasource.user

import com.gustavohisan.apelieuser.api.endpoints.user.UserEndpoints
import com.gustavohisan.apelieuser.api.mapper.login.LoginStateMapper
import com.gustavohisan.apelieuser.api.mapper.register.RegisterStateMapper
import com.gustavohisan.apelieuser.api.model.login.LoginErrorType
import com.gustavohisan.apelieuser.api.model.login.LoginState
import com.gustavohisan.apelieuser.api.model.login.LoginUserData
import com.gustavohisan.apelieuser.api.model.register.RegisterErrorType
import com.gustavohisan.apelieuser.api.model.register.RegisterState
import com.gustavohisan.apelieuser.api.model.register.RegisterUserData
import com.gustavohisan.apelieuser.api.factory.ApiFactory
import com.gustavohisan.apelieuser.repository.datasource.login.UserApiDataSource
import timber.log.Timber
import com.gustavohisan.apelieuser.repository.model.login.LoginState as RepoLoginState
import com.gustavohisan.apelieuser.repository.model.register.RegisterState as RepoRegisterState

/**
 * Implementation of [UserApiDataSource].
 *
 * @param apiFactory factory used to provide the retrofit instance
 * @param loginStateMapper mapper used to map the login state
 * @param registerStateMapper mapper used to map the register state
 */
internal class UserApiDataSourceImpl(
    apiFactory: ApiFactory,
    private val loginStateMapper: LoginStateMapper,
    private val registerStateMapper: RegisterStateMapper
) : UserApiDataSource {

    private val endpoint = apiFactory.getRetrofitInstance().create(UserEndpoints::class.java)

    override suspend fun validateLogin(email: String, password: String): RepoLoginState {
        val callback = endpoint.validateUserLogin(LoginUserData(email, password))
        Timber.d("validateLogin - requestCode = ${callback.code()}")
        return loginStateMapper.toRepo(
            when (callback.code()) {
                403 -> LoginState.Error(LoginErrorType.WRONG_PASSWORD)
                200 -> LoginState.Success(callback.headers().get("Authorization").orEmpty())
                else -> LoginState.Error(LoginErrorType.SERVER_UNAVAILABLE)
            }
        )
    }

    override suspend fun validateToken(token: String): Boolean {
        val callback = endpoint.validateUserToken(token)
        val isValid = callback.isSuccessful
        Timber.d("validateToken - requestCode = ${callback.code()} - isValid = $isValid")
        return isValid
    }

    override suspend fun subscribeUser(
        email: String,
        password: String,
        name: String
    ): RepoRegisterState {
        val callback = endpoint.insertUser(RegisterUserData(name, email, password, ""))
        Timber.d("subscribeUser - requestCode = ${callback.code()}")
        return registerStateMapper.toRepo(
            when (callback.code()) {
                201 -> RegisterState.Success
                415 -> RegisterState.Error(RegisterErrorType.ALREADY_SUBSCRIBED)
                else -> RegisterState.Error(RegisterErrorType.SERVER_UNAVAILABLE)
            }
        )
    }
}
