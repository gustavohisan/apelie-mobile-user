package com.gustavohisan.apelieuser.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gustavohisan.apelieuser.domain.usecase.login.ValidateLogin
import com.gustavohisan.apelieuser.login.mapper.LoginStateMapper
import com.gustavohisan.apelieuser.login.model.LoginState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Login view model.
 *
 * @param validateLogin use-case to retrieve if the login is valid
 * @param loginStateMapper mapper used to map the login state
 */
internal class LoginViewModel(
    private val validateLogin: ValidateLogin,
    private val loginStateMapper: LoginStateMapper
) : ViewModel() {

    // Live data storing the login state
    private val _loginState: MutableLiveData<LoginState> = MutableLiveData()

    val loginState: LiveData<LoginState>
        get() = _loginState

    /**
     * Sets the login state to checking and validates if the login and email are subscribed.
     */
    fun validateUserLogin(email: String, password: String) {
        Timber.d("validateUserLogin - email = $email - password = $password")

        _loginState.value = LoginState.Checking
        viewModelScope.launch(Dispatchers.IO) {
            _loginState.postValue(loginStateMapper.toPresentation(validateLogin(email, password)))
        }
    }
}
