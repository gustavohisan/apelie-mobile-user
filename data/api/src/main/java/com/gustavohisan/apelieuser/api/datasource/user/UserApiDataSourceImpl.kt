package com.gustavohisan.apelieuser.api.datasource.user

import com.gustavohisan.apelieuser.api.mapper.login.LoginStateMapper
import com.gustavohisan.apelieuser.api.mapper.register.RegisterStateMapper
import com.gustavohisan.apelieuser.api.model.login.LoginState
import com.gustavohisan.apelieuser.api.model.register.RegisterState
import com.gustavohisan.apelieuser.repository.datasource.login.UserApiDataSource
import com.gustavohisan.apelieuser.repository.model.login.LoginState as RepoLoginState
import com.gustavohisan.apelieuser.repository.model.register.RegisterState as RepoRegisterState

/**
 * Implementation of [UserApiDataSource].
 *
 * @param loginStateMapper mapper used to map the login state
 * @param registerStateMapper mapper used to map the register state
 */
internal class UserApiDataSourceImpl(
    private val loginStateMapper: LoginStateMapper,
    private val registerStateMapper: RegisterStateMapper
) : UserApiDataSource {

    override fun validateLogin(email: String, password: String): RepoLoginState =
        loginStateMapper.toRepo(
            LoginState.Success(0)
        )

    override fun validateToken(token: Int): Boolean {
        return true
    }

    override fun subscribeUser(email: String, password: String, name: String): RepoRegisterState =
        registerStateMapper.toRepo(
            RegisterState.Success
        )
}
