package com.gustavohisan.apelieuser.api.datasource.login

import com.gustavohisan.apelieuser.api.mapper.login.LoginStateMapper
import com.gustavohisan.apelieuser.api.model.login.LoginState
import com.gustavohisan.apelieuser.repository.datasource.login.UserApiDataSource
import com.gustavohisan.apelieuser.repository.model.login.LoginState as RepositoryState

/**
 * Implementation of [UserApiDataSource].
 *
 * @param loginStateMapper mapper used to map the login state
 */
internal class UserApiDataSourceImpl(
    private val loginStateMapper: LoginStateMapper
) : UserApiDataSource {

    override fun validateLogin(email: String, password: String): RepositoryState =
        loginStateMapper.toRepo(
            LoginState.Success(0)
        )
}
