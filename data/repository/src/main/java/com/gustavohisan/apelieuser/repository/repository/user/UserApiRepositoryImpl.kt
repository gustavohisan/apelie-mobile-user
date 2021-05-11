package com.gustavohisan.apelieuser.repository.repository.user

import com.gustavohisan.apelieuser.domain.model.login.LoginState
import com.gustavohisan.apelieuser.domain.repository.user.UserApiRepository
import com.gustavohisan.apelieuser.repository.datasource.login.UserApiDataSource
import com.gustavohisan.apelieuser.repository.mapper.login.LoginStateMapper

/**
 * implementation of [UserApiRepository].
 *
 * @param loginStateMapper mapper used to map the login state
 * @param userApiDataSource data source used to get user information with the API
 */
internal class UserApiRepositoryImpl(
    private val loginStateMapper: LoginStateMapper,
    private val userApiDataSource: UserApiDataSource
) : UserApiRepository {

    override fun validateUserLogin(email: String, password: String): LoginState =
        loginStateMapper.toDomain(userApiDataSource.validateLogin(email, password))

    override fun validateUserToken(token: Int): Boolean =
        userApiDataSource.validateToken(token)
}
