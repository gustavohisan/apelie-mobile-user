package com.gustavohisan.apelieuser.checkin.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gustavohisan.apelieuser.checkin.state.CheckinState
import com.gustavohisan.apelieuser.domain.usecase.splash.CheckIfUserIsLoggedIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Check-in view model.
 *
 * @param checkIfUserIsLoggedIn use-case used to check if there is an valid user stored
 */
internal class CheckinViewModel(
    private val checkIfUserIsLoggedIn: CheckIfUserIsLoggedIn
) : ViewModel() {

    // Live data to store whether the user is logged in with a valid token
    private val _isLoginValid: MutableLiveData<CheckinState> = MutableLiveData()

    val isLoginValid: LiveData<CheckinState>
        get() = _isLoginValid

    /**
     * Checks whether there is a valid user stored in the device and updates the live data with the
     * result.
     */
    fun getIfUserIsLoggedIn() {
        Timber.d("getIfUserIsLoggedIn")

        viewModelScope.launch(Dispatchers.IO) {
            _isLoginValid.postValue(
                if (checkIfUserIsLoggedIn()) {
                    CheckinState.LoggedIn
                } else {
                    CheckinState.NotLogged
                }
            )
        }
    }
}
