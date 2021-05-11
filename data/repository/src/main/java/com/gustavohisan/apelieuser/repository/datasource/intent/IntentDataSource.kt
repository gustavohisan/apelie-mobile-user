package com.gustavohisan.apelieuser.repository.datasource.intent

/**
 * Intent data source used to provide intents information.
 */
interface IntentDataSource {

    /**
     * Returns the main screen intent action.
     *
     * @return a [String] with the action
     */
    fun getMainScreenIntentAction(): String

    /**
     * Returns the login screen intent action.
     *
     * @return a [String] with the action
     */
    fun getLoginScreenIntentAction(): String
}
