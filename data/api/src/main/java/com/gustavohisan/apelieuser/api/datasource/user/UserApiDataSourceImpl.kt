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
import com.gustavohisan.apelieuser.api.mapper.address.GetUserAddressesStateMapper
import com.gustavohisan.apelieuser.api.mapper.order.GetOrderByIdStateMapper
import com.gustavohisan.apelieuser.api.mapper.order.GetUserOrdersStateMapper
import com.gustavohisan.apelieuser.api.model.address.AddressData
import com.gustavohisan.apelieuser.api.model.address.EditAddressData
import com.gustavohisan.apelieuser.api.model.orders.Rate
import com.gustavohisan.apelieuser.api.model.address.GetUserAddressesState as ApiGetUserAddressesState
import com.gustavohisan.apelieuser.api.model.orders.GetUserOrdersState as ApiGetUserOrdersState
import com.gustavohisan.apelieuser.api.model.orders.GetOrderByIdState as ApiGetOrderByIdState
import com.gustavohisan.apelieuser.repository.datasource.login.UserApiDataSource
import com.gustavohisan.apelieuser.repository.model.address.GetUserAddressesState
import com.gustavohisan.apelieuser.repository.model.order.GetOrderByIdState
import com.gustavohisan.apelieuser.repository.model.order.GetUserOrdersState
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
    private val apiFactory: ApiFactory,
    private val loginStateMapper: LoginStateMapper,
    private val registerStateMapper: RegisterStateMapper,
    private val getUserAddressesStateMapper: GetUserAddressesStateMapper,
    private val getUserOrdersStateMapper: GetUserOrdersStateMapper,
    private val getOrderByIdStateMapper: GetOrderByIdStateMapper
) : UserApiDataSource {

    private val endpoint = apiFactory.getRetrofitInstance().create(UserEndpoints::class.java)

    override suspend fun validateLogin(email: String, password: String): RepoLoginState {
        val callback = endpoint.validateUserLogin(LoginUserData(email, password))
        Timber.d("validateLogin - requestCode = ${callback.code()}")
        return loginStateMapper.toRepo(
            when (callback.code()) {
                401 -> LoginState.Error(LoginErrorType.WRONG_PASSWORD)
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

    override suspend fun setUserToken(token: String) {
        apiFactory.setAuthenticationToken(token)
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
                409 -> RegisterState.Error(RegisterErrorType.ALREADY_SUBSCRIBED)
                else -> RegisterState.Error(RegisterErrorType.SERVER_UNAVAILABLE)
            }
        )
    }

    override suspend fun insertAddress(
        city: String,
        complement: String,
        district: String,
        number: String,
        state: String,
        street: String,
        zipCode: String
    ): Boolean {
        val callback = endpoint.insertAddress(
            AddressData(
                city,
                complement,
                district,
                number,
                state,
                street,
                zipCode
            )
        )
        Timber.d("insertAddress - requestCode = ${callback.code()}")
        return callback.code() == 201
    }

    override suspend fun editAddress(
        addressId: Int,
        city: String,
        complement: String,
        district: String,
        number: String,
        state: String,
        street: String,
        zipCode: String
    ): Boolean {
        val callback = endpoint.editAddress(
            EditAddressData(
                addressId,
                city,
                complement,
                district,
                number,
                state,
                street,
                zipCode
            )
        )
        Timber.d("editAddress - requestCode = ${callback.code()}")
        return callback.code() == 201
    }

    override suspend fun deleteAddress(addressId: Int): Boolean {
        val callback = endpoint.deleteAddress(addressId)
        Timber.d("deleteAddress - requestCode = ${callback.code()}")
        return callback.code() == 201
    }

    override suspend fun getUserAddresses(): GetUserAddressesState {
        val callback = endpoint.getUserAddresses()
        Timber.d("getUserAddresses - requestCode = ${callback.code()}")
        return getUserAddressesStateMapper.toRepo(
            if (callback.code() == 200) {
                ApiGetUserAddressesState.Success(callback.body() ?: listOf())
            } else {
                ApiGetUserAddressesState.Error
            }
        )
    }

    override suspend fun getUserOrders(): GetUserOrdersState {
        val callback = endpoint.getOrders()
        Timber.d("getUserOrders - requestCode = ${callback.code()}")
        return getUserOrdersStateMapper.toRepo(
            if (callback.code() == 200) {
                ApiGetUserOrdersState.Success(callback.body() ?: listOf())
            } else {
                ApiGetUserOrdersState.Error
            }
        )
    }

    override suspend fun getOrderById(id: Int): GetOrderByIdState {
        val callback = endpoint.getOrderById(id)
        val body = callback.body()
        Timber.d("getUserOrders - requestCode = ${callback.code()}")
        return getOrderByIdStateMapper.toRepo(
            if (callback.code() == 200 && body != null) {
                ApiGetOrderByIdState.Success(body)
            } else {
                ApiGetOrderByIdState.Error
            }
        )
    }

    override suspend fun rateOrder(id: Int, rating: Int, description: String): Boolean {
        Timber.d("rateOrder - id = $id - rating = $rating - description = $description")
        val callback = endpoint.rateOrder(id, Rate(description, rating.toFloat()))
        callback.body()
        Timber.d("rateOrder - requestCode = ${callback.code()}")
        return callback.isSuccessful
    }
}
