package com.gustavohisan.apelieuser.repository.model.login

/**
 * Represents the types of error the login check can return.
 */
enum class LoginErrorType {
    WRONG_PASSWORD,
    NOT_SUBSCRIBED,
    SERVER_UNAVAILABLE
}
