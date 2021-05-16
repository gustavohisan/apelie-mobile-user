package com.gustavohisan.apelieuser.memory.loader.intent

/**
 * Loader used to retrieve the login screen intent action.
 */
internal class LoginScreenIntentLoader {

    /**
     * Returns the login screen intent action.
     *
     * @return a [String] with the action
     */
    fun getLoginScreenIntentAction(): String = INTENT_ACTION

    private companion object {

        private const val INTENT_ACTION = "com.gustavohisan.apelieuser.START_LOGIN"
    }
}
