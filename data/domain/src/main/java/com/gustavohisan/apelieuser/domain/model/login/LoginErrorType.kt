package com.gustavohisan.apelieuser.domain.model.login

/**
 * Represents the types of error the login check can return.
 */
enum class LoginErrorType {
    WRONG_PASSWORD,
    NOT_SUBSCRIBED,
    SERVER_UNAVAILABLE
}
