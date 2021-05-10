package com.gustavohisan.apelieuser.core.extensions

import android.util.Patterns

/**
 * Extension used to check if a given [String] is a valid email.
 *
 * @return if the string is valid
 */
fun String.isValidEmail(): Boolean =
    Patterns.EMAIL_ADDRESS.matcher(this).matches() && this.isEmpty().not()

/**
 * Extension used to check if a given [String] is a valid password.
 *
 * @return if the string is valid
 */
fun String.isValidPassword(): Boolean =
    this.length >= 8
