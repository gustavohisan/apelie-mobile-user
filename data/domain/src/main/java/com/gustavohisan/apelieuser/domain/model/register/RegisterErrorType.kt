package com.gustavohisan.apelieuser.domain.model.register

/**
 * Represents the types of error the register check can return.
 */
enum class RegisterErrorType {
    PASSWORDS_DONT_MATCH,
    ALREADY_SUBSCRIBED,
    SERVER_UNAVAILABLE,
    INVALID_EMAIL,
    INVALID_PASSWORD,
    INVALID_NAME
}
