package com.gustavohisan.apelieuser.memory.loader.intent

/**
 * Loader used to retrieve the main screen intent action.
 */
internal class LoginScreenIntentLoader {

    /**
     * Returns the login screen intent action.
     *
     * @return a [String] with the action
     */
    fun getLoginScreenIntentAction(): String = INTENT_ACTIOn

    private companion object {

        private const val INTENT_ACTIOn = "com.gustavohisan.apelieuser.START_LOGIN"
    }
}
