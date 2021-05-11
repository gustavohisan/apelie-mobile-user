package com.gustavohisan.apelieuser.splash.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gustavohisan.apelieuser.domain.usecase.intent.LoadLoginScreenIntent
import com.gustavohisan.apelieuser.domain.usecase.intent.LoadMainScreenIntent
import com.gustavohisan.apelieuser.domain.usecase.splash.CheckIfUserIsLoggedIn
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Splash view model.
 *
 * @param checkIfUserIsLoggedIn use-case used to check if there is an valid user stored
 * @param loadLoginScreenIntent use-case used to load the login screen intent action
 * @param loadMainScreenIntent use-case used to load the main screen intent action
 */
internal class SplashViewModel(
    private val checkIfUserIsLoggedIn: CheckIfUserIsLoggedIn,
    private val loadMainScreenIntent: LoadMainScreenIntent,
    private val loadLoginScreenIntent: LoadLoginScreenIntent
) : ViewModel() {

    // Live data to store whether the user is logged in with a valid token
    private val _isLoginValid: MutableLiveData<Boolean> = MutableLiveData()

    val isLoginValid: LiveData<Boolean>
        get() = _isLoginValid

    /**
     * Checks whether there is a valid user stored in the device and updates the live data with the
     * result.
     */
    fun getIfUserIsLoggedIn() {
        Timber.d("getIfUserisLoggedIn")

        viewModelScope.launch {
            _isLoginValid.postValue(checkIfUserIsLoggedIn())
        }
    }

    fun loadMainScreenIntentAction(): String = loadMainScreenIntent()

    fun loadLoginScreenIntentAction(): String = loadLoginScreenIntent()
}
