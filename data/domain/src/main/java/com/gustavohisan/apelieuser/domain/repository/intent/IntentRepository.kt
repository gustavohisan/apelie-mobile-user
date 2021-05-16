package com.gustavohisan.apelieuser.domain.repository.intent

/**
 * Intent repository used to load intents information.
 */
interface IntentRepository {

    /**
     * Returns the main screen intent action.
     *
     * @return a [String] with the action
     */
    fun loadMainScreenIntentAction(): String

    /**
     * Returns the login screen intent action.
     *
     * @return a [String] with the action
     */
    fun loadLoginScreenIntentAction(): String

    /**
     * Returns the register screen intent action.
     *
     * @return a [String] with the action
     */
    fun loadRegisterScreenIntentAction(): String
}
