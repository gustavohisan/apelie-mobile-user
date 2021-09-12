package com.gustavohisan.apelieuser.checkin.state

internal sealed class CheckinState {

    object LoggedIn: CheckinState()

    object NotLogged: CheckinState()

    object Loading: CheckinState()
}
