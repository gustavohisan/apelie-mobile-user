package com.gustavohisan.apelieuser.register.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gustavohisan.apelieuser.domain.usecase.register.RegisterUser
import com.gustavohisan.apelieuser.register.mapper.RegisterStateMapper
import com.gustavohisan.apelieuser.register.model.RegisterState
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Register view model.
 *
 * @param registerUser use-case used to subscribe a new user in the system
 * @param registerStateMapper mapper used to map the register state
 */
internal class RegisterViewModel(
    private val registerUser: RegisterUser,
    private val registerStateMapper: RegisterStateMapper
) : ViewModel() {

    // Live data storing the register state
    private val _registerState: MutableLiveData<RegisterState> = MutableLiveData()

    val registerState: LiveData<RegisterState>
        get() = _registerState

    /**
     * Registers a new user in the system and updates the state with the result.
     *
     * @param email a [String] containing the email
     * @param password a [String] containing the password
     * @param name a [String] containing the name
     */
    fun registerNewUser(email: String, password: String, name: String) {
        Timber.d("registerNewUser - email = $email - password = $password - name = $name")

        _registerState.value = RegisterState.Checking
        viewModelScope.launch {
            _registerState.postValue(
                registerStateMapper.toPresentation(registerUser(email, password, name))
            )
        }
    }

    /**
     * Updates the register state with the given param.
     *
     * @param eventState the new [RegisterState]
     */
    fun setRegisterState(eventState: RegisterState) {
        Timber.d("setRegisterState - eventState = $eventState")

        _registerState.value = eventState
    }
}
