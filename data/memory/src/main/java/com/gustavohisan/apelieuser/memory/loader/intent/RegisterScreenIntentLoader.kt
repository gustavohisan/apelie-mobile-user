package com.gustavohisan.apelieuser.memory.loader.intent

/**
 * Loader used to retrieve the register screen intent action.
 */
internal class RegisterScreenIntentLoader {

    /**
     * Returns the register screen intent action.
     *
     * @return a [String] with the action
     */
    fun getRegisterScreenIntentAction(): String = INTENT_ACTION

    private companion object {

        private const val INTENT_ACTION = "com.gustavohisan.apelieuser.START_REGISTER"
    }
}
