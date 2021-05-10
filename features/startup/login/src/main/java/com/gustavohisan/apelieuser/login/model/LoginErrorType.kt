package com.gustavohisan.apelieuser.login.model

/**
 * Represents the types of error the login check can return.
 */
enum class LoginErrorType {
    WRONG_PASSWORD,
    NOT_SUBSCRIBED,
    SERVER_UNAVAILABLE,
    INVALID_EMAIL,
    INVALID_PASSWORD
}
