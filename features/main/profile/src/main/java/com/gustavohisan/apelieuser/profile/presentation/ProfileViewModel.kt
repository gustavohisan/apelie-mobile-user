package com.gustavohisan.apelieuser.profile.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gustavohisan.apelieuser.domain.usecase.login.LogoutUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class ProfileViewModel(private val logoutUser: LogoutUser) : ViewModel() {

    private val _logoutUserResult: MutableLiveData<Boolean> = MutableLiveData()
    val logoutUserResult: LiveData<Boolean>
        get() = _logoutUserResult

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            _logoutUserResult.postValue(logoutUser())
        }
    }
}
