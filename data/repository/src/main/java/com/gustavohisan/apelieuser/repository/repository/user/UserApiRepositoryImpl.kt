package com.gustavohisan.apelieuser.repository.repository.user

import com.gustavohisan.apelieuser.domain.model.login.LoginState
import com.gustavohisan.apelieuser.domain.model.register.RegisterState
import com.gustavohisan.apelieuser.domain.repository.user.UserApiRepository
import com.gustavohisan.apelieuser.repository.datasource.login.UserApiDataSource
import com.gustavohisan.apelieuser.repository.mapper.login.LoginStateMapper
import com.gustavohisan.apelieuser.repository.mapper.register.RegisterStateMapper

/**
 * implementation of [UserApiRepository].
 *
 * @param loginStateMapper mapper used to map the login state
 * @param registerStateMapper mapper used to map the register state
 * @param userApiDataSource data source used to get user information with the API
 */
internal class UserApiRepositoryImpl(
    private val loginStateMapper: LoginStateMapper,
    private val registerStateMapper: RegisterStateMapper,
    private val userApiDataSource: UserApiDataSource
) : UserApiRepository {

    override fun validateUserLogin(email: String, password: String): LoginState =
        loginStateMapper.toDomain(userApiDataSource.validateLogin(email, password))

    override fun validateUserToken(token: Int): Boolean =
        userApiDataSource.validateToken(token)

    override fun registerUser(email: String, password: String, name: String): RegisterState =
        registerStateMapper.toDomain(userApiDataSource.subscribeUser(email, password, name))
}
